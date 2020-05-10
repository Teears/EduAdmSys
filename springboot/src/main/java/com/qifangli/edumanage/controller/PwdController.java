package com.qifangli.edumanage.controller;

import com.alibaba.fastjson.JSONObject;
import com.qifangli.edumanage.dao.entity.Student;
import com.qifangli.edumanage.dao.entity.Teacher;
import com.qifangli.edumanage.service.StudentService;
import com.qifangli.edumanage.service.TeacherService;
import com.qifangli.edumanage.util.JWTUtil;
import com.qifangli.edumanage.util.result.Result;
import com.qifangli.edumanage.util.result.ResultUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/pwd")
public class PwdController {
    @Resource
    private StudentService studentService;
    @Resource
    private TeacherService teacherService;

    @PostMapping(value = "changePwd")
    public Result changePwd(@RequestBody JSONObject param, HttpServletRequest request){
        String oldPass = param.getString("oldPass");
        String newPass = param.getString("newPass");
        String checkPass = param.getString("checkPass");
        String token = request.getHeader("token");
        String id = JWTUtil.getUsername(token);
        if(!newPass.equals(checkPass)){
            return ResultUtils.error(-1,"两次密码输入不相同");
        }
        Subject subject = SecurityUtils.getSubject();
        if(subject.hasRole( "student" )){
            Student student = studentService.findStudentById(id);
            if(oldPass.equals(student.getPass())){
                studentService.updatePwdByStuId(id,newPass);
                return ResultUtils.success();
            }
        }else {
            Teacher teacher = teacherService.findTeacherById(id);
            if(oldPass.equals(teacher.getPass())){
                teacherService.updatePwdByTeaId(id,newPass);
                return ResultUtils.success();
            }
        }

        return ResultUtils.error(-2,"用户不存在");
    }
}
