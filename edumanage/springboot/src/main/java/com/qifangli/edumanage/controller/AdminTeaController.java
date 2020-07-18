package com.qifangli.edumanage.controller;

import com.alibaba.fastjson.JSONObject;
import com.qifangli.edumanage.dao.entity.Teacher;
import com.qifangli.edumanage.service.TeacherService;
import com.qifangli.edumanage.util.ExcelUtils;
import com.qifangli.edumanage.util.JWTUtil;
import com.qifangli.edumanage.util.LoggerUtil;
import com.qifangli.edumanage.util.result.Result;
import com.qifangli.edumanage.util.result.ResultUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
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
@RequestMapping("/teaAdmin")
public class AdminTeaController {

    @Resource
    private TeacherService teacherService;

    /**
     * 获取教师列表
     * @param param
     * @return
     */
    @RequiresPermissions("admin_teaAdmin")
    @PostMapping("/getTeaData")
    public Result getStuInfo(@RequestBody JSONObject param){
        String dpt = param.getString("dpt");
        List<Teacher> teachers = teacherService.findTeacherByDpt(dpt);
        return ResultUtils.success(teachers);
    }

    /**
     * 添加单个教师
     * @param param
     * @return
     */
    @RequiresPermissions("admin_teaAdmin")
    @PostMapping("/addTeacher")
    public Result addTeacher(@RequestBody JSONObject param){
        String id = param.getString("id");
        String name = param.getString("name");
        String sex = param.getString("sex");
        String birth = param.getString("birth");
        String degree = param.getString("degree");
        String idCard = param.getString("idCard");
        String telephone = param.getString("telephone");
        String political = param.getString("political");
        String workTime = param.getString("workTime");
        String department = param.getString("department");
        String title = param.getString("title");

        Teacher teacher = new Teacher(id,name,sex,degree,title,birth,idCard,telephone,political,department,workTime);
        try {
            teacherService.insertTea(teacher);
            return ResultUtils.success();
        }catch (Exception e){
            return ResultUtils.error(-1,"添加数据有误，请检查！");
        }
    }

    /**
     * 按工号删除老师
     * @param param
     * @return
     */
    @RequiresPermissions("admin_stuAdmin")
    @PostMapping("/deleteTea")
    public Result deleteTea(@RequestBody JSONObject param){
        String id = param.getString("id");
        try {
            if(teacherService.deleteTeaById(id)>0){
                return ResultUtils.success();
            }else {
                return ResultUtils.error(-1,"要删除的数据不存在");
            }
        }catch (Exception e){
            return ResultUtils.error(-1,"不能删除该教师");
        }
    }

    /**
     * 编辑教师信息
     * @param param
     * @return
     */
    @RequiresPermissions("admin_stuAdmin")
    @PostMapping("/editTea")
    public Result editStu(@RequestBody JSONObject param){
        String id = param.getString("id");
        String name = param.getString("name");
        String sex = param.getString("sex");
        String birth = param.getString("birth");
        String degree = param.getString("degree");
        String workTime = param.getString("workTime");
        String title = param.getString("title");
        String idCard = param.getString("idCard");
        String political = param.getString("political");
        String telephone = param.getString("telephone");
        String department = param.getString("department");

        Teacher teacher = new Teacher(id,name,sex,degree,title,birth,idCard,telephone,political,department,workTime);
        try {
            if(teacherService.updateTea(teacher)>0){
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
     * excel导入
     * @param file
     * @return
     */
    @RequiresPermissions("admin_teaAdmin")
    @RequestMapping("/upload")
    public Result teaUpload(@RequestParam("file") MultipartFile file){
        InputStream is = null;
        Map<String,Object> map= new HashMap<>();
        try{
            is = file.getInputStream();
            List<Object> teacherList = ExcelUtils.importDataFromExcel(new Teacher(),is,file.getOriginalFilename());
            if(teacherList.size()==0){
                return ResultUtils.error(-1,"导入数据不能为空");
            }else{
                Map<String,Object> resmap = teacherService.addTea(teacherList);
                int totalNum = teacherList.size();
                int failed = totalNum - Integer.parseInt(resmap.get("success").toString());
                map.put("success",resmap.get("success"));
                map.put("totalNum",totalNum);
                map.put("failed",failed);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        LoggerUtil.info("- 导入教师Excel表operator");
        return ResultUtils.success(map);
    }

}
