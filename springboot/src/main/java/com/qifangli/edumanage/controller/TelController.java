package com.qifangli.edumanage.controller;

import com.alibaba.fastjson.JSONObject;
import com.qifangli.edumanage.dao.entity.Student;
import com.qifangli.edumanage.dao.entity.Teacher;
import com.qifangli.edumanage.service.StudentService;
import com.qifangli.edumanage.service.TeacherService;
import com.qifangli.edumanage.util.JWTUtil;
import com.qifangli.edumanage.util.LoggerUtil;
import com.qifangli.edumanage.util.SmsUtil;
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
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/phone")
public class TelController {

    @Resource
    private StudentService studentService;
    @Resource
    private TeacherService teacherService;

    /**
     * 重置密码时发送验证码
     * @param param
     * @param session
     * @return
     */
    @PostMapping(value = "sendCode")
    public Result sendCode(@RequestBody JSONObject param, HttpSession session){
        String id = param.getString("id");
        String phoneNumber = param.getString("phoneNumber");
        String telephone;

        if(id.length()==10){
            Student student = studentService.findStudentById(id);
            telephone = student.getTelephone();
        }else {
            Teacher teacher = teacherService.findTeacherById(id);
            telephone = teacher.getTelephone();
        }
        if(telephone==null){
            return ResultUtils.error(-1,"用户不存在！");
        }
        if(!phoneNumber.equals(telephone)){
            return ResultUtils.error(-1,"手机号错误！");
        }
        SmsUtil smsUtil = new SmsUtil();
        String phoneCode = smsUtil.SendMessage(phoneNumber);
        session.setAttribute("codeTime", LocalDateTime.now());
        session.setAttribute("phoneCode", phoneCode);
        LoggerUtil.info("- 发送验证码：id"+id);
        return ResultUtils.success();
    }

    /**
     * 更换手机号时发送验证码
     * @param param
     * @param request
     * @return
     */
    @PostMapping(value = "sendChangePhoneCode")
    public Result sendChangePhoneCode(@RequestBody JSONObject param, HttpServletRequest request){
        HttpSession session = request.getSession();
        String token = request.getHeader("token");
        String id = JWTUtil.getUsername(token);
        String phoneNumber = param.getString("phoneNumber");
        try {
            SmsUtil smsUtil = new SmsUtil();
            String phoneCode = smsUtil.SendMessage(phoneNumber);
            session.setAttribute("phoneCode", phoneCode);
            session.setAttribute("phoneNumber", phoneNumber);
            session.setAttribute("codeTime", LocalDateTime.now());
            LoggerUtil.info("- 发送验证码：id"+id);
            return  ResultUtils.success();
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtils.error(-1,"验证码发送失败");
        }
    }

}
