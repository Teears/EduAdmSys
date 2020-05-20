package com.qifangli.edumanage.controller;

import com.qifangli.edumanage.util.result.ResultUtils;
import com.qifangli.edumanage.util.result.Result;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/unauthorized")
public class UnauthorizedurlController {
    @RequestMapping("noPerm")
    public Result unauthorized(){
        return ResultUtils.error(0,"no unauthorized");
    }
    @RequestMapping("logout")
    public Result logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return ResultUtils.error(302,"logout");
    }

    @RequestMapping("noLogin")
    public Result unidentified(){
        return ResultUtils.error(0,"请先登录");
    }
}
