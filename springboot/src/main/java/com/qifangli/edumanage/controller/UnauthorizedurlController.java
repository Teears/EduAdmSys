package com.qifangli.edumanage.controller;

import com.qifangli.edumanage.util.result.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/unauthorized")
public class UnauthorizedurlController {
    @RequestMapping("")
    public Result unauthorized(){
        Result result = new Result();
        result.setCode(0);
        result.setMsg("not authorized");
        return result;
    }
}
