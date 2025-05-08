package com.qifangli.edumanage.controller;

import com.alibaba.fastjson.JSONObject;
import com.qifangli.edumanage.dao.entity.Course;
import com.qifangli.edumanage.service.CourseService;
import com.qifangli.edumanage.util.ExcelUtils;
import com.qifangli.edumanage.util.JWTUtil;
import com.qifangli.edumanage.util.LoggerUtil;
import com.qifangli.edumanage.util.result.Result;
import com.qifangli.edumanage.util.result.ResultUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController()
@RequestMapping("/crsAdmin")
public class AdminCrsController {

    @Resource
    private CourseService courseService;

    /**
     * 获取学院的课程信息
     * @param param
     * @return
     */
    @RequiresPermissions("admin_crsAdmin")
    @PostMapping("/getCourseInfo")
    public Result getCourseInfo(@RequestBody JSONObject param){
        String dpt = param.getString("dpt");
        List<Course> courses = courseService.findCourseByDpt(dpt);
        return ResultUtils.success(courses);
    }

    /**
     * 添加单个课程
     * @param param
     * @return
     */
    @RequiresPermissions("admin_crsAdmin")
    @PostMapping("/addCourseData")
    public Result addCourseData(@RequestBody JSONObject param) {
        String name = param.getString("name");
        String department = param.getString("dpt");
        String type = param.getString("type");
        String hours =param.getString("hours");
        String credit =param.getString("credit");
        Course course = new Course(name,credit,hours,department,type);
        try {
            courseService.insertCrs(course);
            return ResultUtils.success();
        }catch (DuplicateKeyException e){
            e.printStackTrace();
            return ResultUtils.error(-1,"课程编号重复");
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtils.error(-1,"添加数据有误，请检查！");
        }
    }

    /**
     * 按ID删除课程信息
     * @param param
     * @return
     */
    @RequiresPermissions("admin_crsAdmin")
    @PostMapping("/deleteCrs")
    public Result deleteCrs(@RequestBody JSONObject param){
        String id = param.getString("id");
        try {
            if(courseService.deleteCrsById(id)>0){
                return ResultUtils.success();
            }else {
                return ResultUtils.error(-1,"要删除的数据不存在");
            }
        }catch (Exception e){
            return ResultUtils.error(-1,"不能删除该门课程");
        }
    }

    /**
     * 编辑课程信息
     * @param param
     * @return
     */
    @RequiresPermissions("admin_crsAdmin")
    @PostMapping("/editCrs")
    public Result editCrs(@RequestBody JSONObject param){
        String id = param.getString("id");
        String name = param.getString("name");
        String department = param.getString("dpt");
        String type = param.getString("type");
        String hours =param.getString("hours");
        String credit =param.getString("credit");
        Course course = new Course(name,credit,hours,department,type,id);
        try {
            if(courseService.updateCrs(course)>0){
                return ResultUtils.success();
            }else {
                return ResultUtils.error(-1,"数据不存在");
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtils.error(-1,"添加的数据有误，请检查！");
        }
    }

    /**
     * excel导入课程信息
     * @param file
     * @return
     */
    @RequiresPermissions("admin_crsAdmin")
    @RequestMapping("/upload")
    public Result crsUpload(@RequestParam("file") MultipartFile file){
        InputStream is = null;
        Map<String,Object> map= new HashMap<>();
        try{
            is = file.getInputStream();
            List<Object> courseList = ExcelUtils.importDataFromExcel(new Course(),is,file.getOriginalFilename());
            if(courseList.size()==0){
                return ResultUtils.error(-1,"导入数据不能为空");
            }else{
                Map<String,Object> resmap = courseService.addCrs(courseList);
                int totalNum = courseList.size();
                int failed = totalNum - Integer.parseInt(resmap.get("success").toString());
                map.put("success",resmap.get("success"));
                map.put("totalNum",totalNum);
                map.put("failed",failed);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        LoggerUtil.info("- 导入课程Excel表");
        return ResultUtils.success(map);
    }

}
