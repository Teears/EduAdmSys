package com.qifangli.edumanage.controller;

import com.alibaba.fastjson.JSONObject;
import com.qifangli.edumanage.dao.entity.Student;
import com.qifangli.edumanage.service.StudentService;
import com.qifangli.edumanage.service.TeacherService;
import com.qifangli.edumanage.util.JWTUtil;
import com.qifangli.edumanage.util.result.Result;
import com.qifangli.edumanage.util.result.ResultUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController()
@RequestMapping("/admin")
public class AdminController {
    @Resource
    private TeacherService teacherService;
    @Resource
    private StudentService studentService;

    @RequiresPermissions("admin_stuAdmin")
    @PostMapping("/stuAdmin")
    public Result getStuInfo(@RequestBody JSONObject param, HttpServletRequest request){
        String token = request.getHeader("token");
        String id = JWTUtil.getUsername(token);
        String dpt = teacherService.findTeacherById(id).getDepartment();
        List<Student> students = studentService.findStuByDpt(dpt);

        return ResultUtils.success(students);
    }

}
