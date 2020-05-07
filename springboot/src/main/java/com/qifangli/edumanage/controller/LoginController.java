package com.qifangli.edumanage.controller;

import com.alibaba.fastjson.JSONObject;
import com.qifangli.edumanage.dao.entity.Student;
import com.qifangli.edumanage.dao.entity.Teacher;
import com.qifangli.edumanage.service.TeacherService;
import com.qifangli.edumanage.util.shiro.JWTUtil;
import com.qifangli.edumanage.util.result.Result;
import com.qifangli.edumanage.util.result.ResultUtils;
import com.qifangli.edumanage.service.StudentService;
import com.qifangli.edumanage.util.yzm.VerifyCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Tears
 */
@RestController
@RequestMapping("/api")
public class LoginController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private TeacherService teacherService;

    @PostMapping(value = "login")
    public Result login(@RequestBody JSONObject param,HttpServletResponse response){
        String id = param.getString("user");
        String pwd = param.getString("pass");
        String vcode = param.getString("vcode").toLowerCase();
        System.out.println("*************登录**************");
//        if(!vcode.equals(session.getAttribute("verCode"))){
//            return ResultUtils.error(-1,"验证码错误");
//        }
        Result result = new Result();
        Map<String,String> data = new HashMap<>();
        if(id.length()==10){
            Student student = studentService.findStudentById(id);
            if(!student.getPass().equals(pwd)){
                return ResultUtils.error(-2,"用户名或密码不正确");
            }
            result.setCode(1);
            result.setMsg("登录成功");
            String token = JWTUtil.sign(student.getId(), student.getPass());
            data.put("token",token);
            result.setDatas(data);
        }else if(id.length()==8){
            Teacher teacher = teacherService.findTeacherById(id);
            if(!teacher.getPass().equals(pwd)){
                return ResultUtils.error(-2,"用户名或密码不正确");
            }
            result.setCode(2);
            result.setMsg("登录成功");
            String token = JWTUtil.sign(teacher.getId(),teacher.getPass());
            data.put("token",token);
            result.setDatas(data);
        }else{
            return ResultUtils.error(-2,"用户名或密码不正确");
        }

        return result;
    }

    @RequestMapping(value = "yzm")
    public void yzm(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        // 生成随机字串
        String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
        // 存入会话session
        HttpSession session = request.getSession(true);
        // 删除以前的
        session.removeAttribute("verCode");
        session.removeAttribute("codeTime");
        session.setAttribute("verCode", verifyCode.toLowerCase());		//生成session
        session.setAttribute("codeTime", LocalDateTime.now());
        System.out.println("***************获取图片验证码****************");
        System.out.println("sessionId="+session.getId());
        // 生成图片
        int w = 100, h = 40;
        OutputStream out = response.getOutputStream();
        VerifyCodeUtils.outputImage(w, h, out, verifyCode);
    }
}