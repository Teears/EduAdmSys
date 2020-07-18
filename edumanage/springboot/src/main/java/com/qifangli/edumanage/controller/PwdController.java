package com.qifangli.edumanage.controller;

import com.alibaba.fastjson.JSONObject;
import com.qifangli.edumanage.dao.entity.Student;
import com.qifangli.edumanage.dao.entity.Teacher;
import com.qifangli.edumanage.service.StudentService;
import com.qifangli.edumanage.util.Duration5Util;
import com.qifangli.edumanage.util.LoggerUtil;
import com.qifangli.edumanage.util.result.ResultUtils;
import com.qifangli.edumanage.service.TeacherService;
import com.qifangli.edumanage.util.JWTUtil;
import com.qifangli.edumanage.util.result.Result;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.Duration;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/pwd")
public class PwdController {
    @Resource
    private StudentService studentService;
    @Resource
    private TeacherService teacherService;

    /**
     * 修改密码
     * @param param oldPass newPass checkPass
     * @param request token
     * @return Result
     */
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
        String msg = "- 修改密码operator:"+id;
        LoggerUtil.info(msg);
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
        return ResultUtils.error(-2,"登录密码错误");
    }

    /**
     * 重置密码，教师学生通用
     * @param param
     * @param session 有效期5分钟
     * @return
     */
    @PostMapping(value = "resetPwd")
    public Result changePwd(@RequestBody JSONObject param, HttpSession session) {
        String id = param.getString("user");
        String newPass = param.getString("pass");
        String checkPass = param.getString("checkPass");
        String phoneCode = param.getString("vcode");
        if (!newPass.equals(checkPass)) {
            return ResultUtils.error(-1, "两次密码输入不相同");
        }
        if (!phoneCode.equals(session.getAttribute("phoneCode"))) {
            return ResultUtils.error(-1, "验证码错误");
        }
        LocalDateTime startTime = (LocalDateTime) session.getAttribute("codeTime");
        if(Duration5Util.isTimeOut(startTime)){
            return ResultUtils.error(-1, "验证码过期");
        }
        if (id.length()==10) {
            if(studentService.updatePwdByStuId(id, newPass)>0){
                return ResultUtils.success();
            }
        } else {
            if(teacherService.updatePwdByTeaId(id, newPass)>0){
                return ResultUtils.success();
            }
        }
        return ResultUtils.error(-1,"用户不存在");
    }

    /**
     * 修改手机号
     * @param param
     * @param request
     * @return
     */
    @PostMapping(value = "changePhoneCode")
    public Result changePhoneCode(@RequestBody JSONObject param,HttpServletRequest request) {
        HttpSession session = request.getSession();
        String token = request.getHeader("token");
        String id = JWTUtil.getUsername(token);
        String code = param.getString("phoneCode");
        String telephone = param.getString("telephone");
        String phoneCode = (String) session.getAttribute("phoneCode");
        String phoneNumber = (String) session.getAttribute("phoneNumber");
        System.out.println(code+telephone);

        System.out.println(phoneCode+phoneNumber);
        if(!phoneCode.equals(code)||!phoneNumber.equals(telephone)){
            return ResultUtils.error(-1,"验证码错误");
        }
        LocalDateTime startTime = (LocalDateTime) session.getAttribute("codeTime");
        if(Duration5Util.isTimeOut(startTime)){
            return ResultUtils.error(-1, "验证码过期");
        }
        Subject subject = SecurityUtils.getSubject();
        if (subject.hasRole("student")) {
            studentService.updateStuTel(id,telephone);
            return ResultUtils.success();
        } else {
            teacherService.updateTeaTel(id,telephone);
            return ResultUtils.success();
        }
    }


}
