package com.qifangli.edumanage.controller;

import com.qifangli.edumanage.util.result.Result;
import com.qifangli.edumanage.util.result.ResultUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/unauthorized")
public class UnauthorizedurlController {
    @RequestMapping("noPerm")
    public Result unauthorized(){
        return ResultUtils.error(0,"no unauthorized");
    }
    @RequestMapping("noLogin")
    public Result unidentified(){
        return ResultUtils.error(0,"请先登录");
    }
}
