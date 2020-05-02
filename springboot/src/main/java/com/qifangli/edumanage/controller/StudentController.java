package com.qifangli.edumanage.controller;

import com.qifangli.edumanage.utli.result.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api")
public class StudentController {

    @RequestMapping("getRoomTable")
    public Result getRoomTable(){
        return null;
    }

}
