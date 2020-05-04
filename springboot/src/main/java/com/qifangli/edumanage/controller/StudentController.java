package com.qifangli.edumanage.controller;

import com.alibaba.fastjson.JSONObject;
import com.qifangli.edumanage.dao.entity.CourseArrange;
import com.qifangli.edumanage.dao.entity.Spot;
import com.qifangli.edumanage.dao.entity.StudentScore;
import com.qifangli.edumanage.service.CourseArrangeService;
import com.qifangli.edumanage.service.SpotService;
import com.qifangli.edumanage.service.StudentService;
import com.qifangli.edumanage.util.result.Result;
import com.qifangli.edumanage.util.result.ResultUtils;
import com.qifangli.edumanage.util.shiro.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RestController()
@RequestMapping("/Student")
public class StudentController {
    @Autowired
    private SpotService spotService;

    @Autowired
    private CourseArrangeService courseArrangeService;

    @Autowired
    private StudentService studentService;

    @PostMapping("getTermTable")
    public Result getTermTable(@RequestBody JSONObject param, HttpServletRequest request){
        String token = request.getHeader("token");
        String id = JWTUtil.getUsername(token);
        if(id == null){
            return ResultUtils.error(0,"登录已过期");
        }
        String term = param.getString("term");
        List<CourseArrange> courseArrange = courseArrangeService.findByTermAndStuId(term,id);
        LinkedList<Map<String,String>> datas = new LinkedList<>();
        for(CourseArrange item : courseArrange){
            Map<String,String> data = new HashMap<>();
            data.put("week",item.getWeek());
            data.put("time",item.getTime());
            Spot spot = spotService.findSpotById(item.getSpot());
            data.put("info",item.getCourseName() + "\n" + item.getTeacherName() + "\n" + spot.getArea()+spot.getRoom());
            datas.add(data);
        }
        return ResultUtils.success(datas);
    }

    @PostMapping("getScoreTable")
    public Result getScoreTable(@RequestBody JSONObject param, HttpServletRequest request){
        String token = request.getHeader("token");
        String id = JWTUtil.getUsername(token);
        if(id == null){
            return ResultUtils.error(0,"登录已过期");
        }
        String term = param.getString("term");
        List<StudentScore> studentScore = studentService.findScoreByTermAndStuId(term,id);

        return ResultUtils.success(studentScore);
    }

}
