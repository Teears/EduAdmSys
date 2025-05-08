package com.qifangli.edumanage.controller;

import com.alibaba.fastjson.JSONObject;
import com.qifangli.edumanage.dao.entity.CourseArrange;
import com.qifangli.edumanage.dao.entity.Term;
import com.qifangli.edumanage.util.ExcelUtils;
import com.qifangli.edumanage.util.JWTUtil;
import com.qifangli.edumanage.util.LoggerUtil;
import com.qifangli.edumanage.util.result.Result;
import com.qifangli.edumanage.util.result.ResultUtils;
import com.qifangli.edumanage.service.CourseArrangeService;
import com.qifangli.edumanage.service.RolePermissionService;
import com.qifangli.edumanage.service.SpotService;
import com.qifangli.edumanage.service.TermService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.dao.DataAccessException;
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
@RequestMapping("/adminArrange")
public class AdminArrangeController {

    @Resource
    private RolePermissionService rolePermissionService;
    @Resource
    private CourseArrangeService courseArrangeService;
    @Resource
    private TermService termService;
    @Resource
    private SpotService spotService;


    /**
     * 排课，判断当前是否开启通道，以及是否是超级管理员
     * @return isOpen,isSuper
     */
    @RequiresPermissions("admin_selectCrs")
    @PostMapping("/arrange/loadButton")
    public Result isStuSelect(){
        Map<String,Object> map= new HashMap<>();
        Subject subject = SecurityUtils.getSubject();
        if(subject.hasRole( "super_admin")){
            map.put("isSuper",1);
        }else {
            map.put("isSuper",0);
        }
        if (rolePermissionService.findStuSelect() != null){
            map.put("isOpen",1);
        }else{
            map.put("isOpen",0);
        }
        return ResultUtils.success(map);
    }

    /**
     * 开启或关闭xx某学期选课通道，只有superAdmin才能
     * @return
     */
    @RequiresPermissions("super_selectCrs")
    @PostMapping("/arrange/openOrCloseStuSelect")
    public Result openOrCloseStuSelect(){
        if (rolePermissionService.findStuSelect() == null){
            rolePermissionService.addStuSelect();
            return ResultUtils.success("选课通道已开启");
        }else {
            rolePermissionService.deleteStuSelect();
            return ResultUtils.success("选课通道已关闭");
        }
    }

    /**
     * 获取xx学院的，xxx年级xxx学年的课程安排
     * @param param,dpt,grade,term
     * @return arranges
     */
    @RequiresPermissions("admin_selectCrs")
    @PostMapping("/arrange/getCrsArrangeData")
    public Result getCrsArrangeData(@RequestBody JSONObject param){
        String dpt = param.getString("dpt");
        String grade = param.getString("grade");
        String term = param.getString("term");
        System.out.println(dpt+"*****"+grade+"*****"+term);
        List<CourseArrange> arranges = courseArrangeService.findByTermDptGrade(term,dpt,grade);

        return ResultUtils.success(arranges);
    }

    /**
     * 导入excel表
     * @param file
     * @return
     */
    @RequiresPermissions("admin_selectCrs")
    @PostMapping("/arrange/uploadCrsArrange")
    public Result uploadCrsArrange(@RequestParam("file") MultipartFile file){
        InputStream is = null;
        Map<String,Object> map= new HashMap<>();
        try{
            is = file.getInputStream();
            List<Object> arrangeList = ExcelUtils.importDataFromExcel(new CourseArrange(),is,file.getOriginalFilename());
            if(arrangeList.size()==0){
                return ResultUtils.error(-1,"导入数据不能为空");
            }else{
                Map<String,Object> resmap = courseArrangeService.addArrange(arrangeList);
                int totalNum = arrangeList.size();
                int failed = totalNum - Integer.parseInt(resmap.get("success").toString());
                map.put("success",resmap.get("success"));
                map.put("totalNum",totalNum);
                map.put("failed",failed);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        LoggerUtil.info("- 导入排课Excel表");
        return ResultUtils.success(map);
    }

    /**
     * 编辑排课
     * @param param
     * @return
     */
    @RequiresPermissions("admin_selectCrs")
    @PostMapping("/arrange/editCrsArrange")
    public Result editCrsArrange(@RequestBody JSONObject param){
        String id = param.getString("id");
        String grade = param.getString("grade");
        String term = param.getString("term");
        String teacherno = param.getString("teacherno");
        String courseno = param.getString("courseno");
        String area = param.getString("area");
        String room = param.getString("room");
        String week = param.getString("week");
        String time = param.getString("time");
        String total = param.getString("total");
        CourseArrange courseArrange = new CourseArrange(id,teacherno,courseno,term,area,room,time,week,total,grade);
        String spot = spotService.findSpotByAreaAndRoom(area,room).getId();
        courseArrange.setSpot(spot);
        if(courseArrangeService.updateArrange(courseArrange)>0){
            return ResultUtils.success();
        }else {
            return ResultUtils.error(-1,"数据不存在");
        }
    }

    /**
     * 删除排课
     * @param param
     * @return
     */
    @RequiresPermissions("admin_selectCrs")
    @PostMapping("/arrange/deleteCrsArrange")
    public Result deleteCrsArrange(@RequestBody JSONObject param){
        String id = param.getString("id");
        if(courseArrangeService.deleteArrangeById(id)>0){
            return ResultUtils.success();
        }else {
            return ResultUtils.error(-1,"错误");
        }
    }

    @RequiresPermissions("super_selectCrs")
    @PostMapping("/arrange/addTerm")
    public Result addTerm(@RequestBody JSONObject param){
        String id = param.getString("id");
        String name = param.getString("name");
        Term term = new Term(id,name);
        try {
            if(termService.insertTerm(term)>0){
                termService.updateActiveTerm(name);
            }
        }catch (DataAccessException e){
            e.printStackTrace();
        }
        return ResultUtils.success();
    }
}
