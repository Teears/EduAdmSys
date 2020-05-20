package com.qifangli.edumanage.controller;

import com.alibaba.fastjson.JSONObject;
import com.qifangli.edumanage.dao.entity.CourseArrange;
import com.qifangli.edumanage.service.CourseArrangeService;
import com.qifangli.edumanage.util.JWTUtil;
import com.qifangli.edumanage.util.result.Result;
import com.qifangli.edumanage.util.result.ResultUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/stuAndTea")
public class TermTableController {
    @Autowired
    private CourseArrangeService courseArrangeService;

    @PostMapping("getTermTable")
    public Result getTermTable(@RequestBody JSONObject param, HttpServletRequest request){
        String token = request.getHeader("token");
        String id = JWTUtil.getUsername(token);
        String term = param.getString("term");
        List<CourseArrange> courseArrange = null;
        Subject subject = SecurityUtils.getSubject();
        if(subject.hasRole( "student" )){
            courseArrange = courseArrangeService.findByTermAndStuId(term,id);
        }else {
            courseArrange = courseArrangeService.findByTermAndTeaId(term,id);
        }
        LinkedList<Map<String,String>> datas = new LinkedList<>();
        for(CourseArrange item : courseArrange){
            Map<String,String> data = new HashMap<>();
            data.put("week",item.getWeek());
            data.put("time",item.getTime());
            data.put("info",item.getCourseName() + "\n" + item.getTeacherName() + "\n" + item.getArea()+item.getRoom()+"["+item.getSelected()+"人]");
            datas.add(data);
        }

        return ResultUtils.success(datas);
    }
}
