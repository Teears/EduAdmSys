package com.qifangli.edumanage.controller;

import com.qifangli.edumanage.util.result.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/unauthorized")
public class UnauthorizedurlController {
    @RequestMapping("noPerm")
    public Result unauthorized(){
        Result result = new Result();
        result.setCode(0);
        result.setMsg("not authorized");
        return result;
    }
    @RequestMapping("noLogin")
    public Result unidentified(){
        Result result = new Result();
        result.setCode(0);
        result.setMsg("请先登录");
        return result;
    }
}
