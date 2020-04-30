package com.qifangli.edumanage.controller;

import com.alibaba.fastjson.JSONObject;
import com.qifangli.edumanage.dao.entity.Student;
import com.qifangli.edumanage.utli.result.Result;
import com.qifangli.edumanage.utli.result.ResultUtils;
import com.qifangli.edumanage.service.StudentService;
import com.qifangli.edumanage.utli.yzm.VerifyCodeUtils;
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

@RestController //将类中所有控制器的方法返回值转为json格式，并响应前端 = @Controller + @responseBody
@RequestMapping("/test")
public class LoginController {
    @Autowired
    private StudentService studentService;

    @RequestMapping(value = "login")
    public Result login(@RequestBody JSONObject param,HttpServletRequest request){
        HttpSession session = request.getSession();
        String id = param.getString("user");
        String pwd = param.getString("pass");
        String vcode = param.getString("vcode");
        System.out.println(id + " " + pwd + " " + vcode);
        if(!vcode.equals(session.getAttribute("verCode"))){
            return ResultUtils.error(-1,"验证码错误");
        }
        Student student = studentService.findStudentByIdAndPwd(id,pwd);
        if(student == null){
            return ResultUtils.error(-2,"用户名或密码不正确");
        }
        Map<String,String> datas = new HashMap<>();
        datas.put("userid",student.getId());
        datas.put("username",student.getName());
        return ResultUtils.success(datas);
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
        // 生成图片
        int w = 100, h = 40;
        OutputStream out = response.getOutputStream();
        VerifyCodeUtils.outputImage(w, h, out, verifyCode);
    }
}