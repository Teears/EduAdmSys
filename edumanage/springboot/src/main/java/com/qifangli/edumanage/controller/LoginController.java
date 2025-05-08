package com.qifangli.edumanage.controller;

import com.alibaba.fastjson.JSONObject;
import com.qifangli.edumanage.util.JWTUtil;
import com.qifangli.edumanage.util.LoggerUtil;
import com.qifangli.edumanage.util.result.Result;
import com.qifangli.edumanage.util.result.ResultUtils;
import com.qifangli.edumanage.util.yzm.VerifyCodeUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
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
@RequestMapping("/login")
public class LoginController {

    @PostMapping(value = "user")
    public Result login(@RequestBody JSONObject param, HttpSession session){
        String id = param.getString("user");
        String pwd = param.getString("pass");
        String vcode = param.getString("vcode").toLowerCase();
//        if(!vcode.equals(session.getAttribute("verCode"))){
//            LoggerUtil.error("- 验证码错误");
//            return ResultUtils.error(-1,"验证码错误");
//        }
        Result result = new Result();
        Subject subject = SecurityUtils.getSubject();
        Map<String,String> data = new HashMap<>();
        UsernamePasswordToken token = new UsernamePasswordToken(id,pwd);
        try{
            subject.login(token);
            System.out.println("登录成功");
            if(subject.hasRole( "student" )){
                result.setCode(1);
                result.setMsg("登录成功");
                data.put("token", JWTUtil.sign(id,pwd));
                result.setDatas(data);
                String msg = "- 登录成功id:"+id;
                LoggerUtil.info(msg);
                return result;
            }else {
                result.setCode(2);
                result.setMsg("登录成功");
                data.put("token",JWTUtil.sign(id,pwd));
                result.setDatas(data);
                String msg = "- 登录成功id:"+id;
                LoggerUtil.info(msg);
                return result;
            }
        }catch (AuthenticationException e){
            String msg = "- 登录失败id:"+id;
            LoggerUtil.error(msg);
            return ResultUtils.error(-1,"用户名或密码错误");
        }
    }

    @PostMapping(value = "admin")
    public Result loginAdmin(@RequestBody JSONObject param,HttpSession session){
        String id = param.getString("user");
        String pwd = param.getString("pass");
        String vcode = param.getString("vcode").toLowerCase();
//        if(!vcode.equals(session.getAttribute("verCode"))){
//            LoggerUtil.error("- 验证码错误");
//            return ResultUtils.error(-1,"验证码错误");
//        }
        Result result = new Result();
        Subject subject = SecurityUtils.getSubject();
        Map<String,String> data = new HashMap<>();
        UsernamePasswordToken token = new UsernamePasswordToken(id,pwd);
        try{
            subject.login(token);
            if(subject.hasRole( "admin" )){
                data.put("perm","1");
            }else if(subject.hasRole( "super_admin" )){
                data.put("perm","2");
            }else {
                subject.hasRole( "super_admin" );
                String msg = "- 登录失败id:"+id;
                LoggerUtil.error(msg);
                return ResultUtils.error(-1,"用户名或密码错误");
            }
            result.setCode(1);
            result.setMsg("登录成功");
            data.put("token",JWTUtil.sign(id,pwd));
            result.setDatas(data);
            String msg = "- 登录成功id:"+id;
            LoggerUtil.info(msg);
            return result;
        }catch (AuthenticationException e){
            String msg = "- 登录失败id:"+id;
            LoggerUtil.error(msg);
            return ResultUtils.error(-1,"用户名或密码错误");
        }
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
        //生成session
        session.setAttribute("verCode", verifyCode.toLowerCase());
        session.setAttribute("codeTime", LocalDateTime.now());
        // 生成图片
        int w = 100, h = 40;
        OutputStream out = response.getOutputStream();
        VerifyCodeUtils.outputImage(w, h, out, verifyCode);
    }
}