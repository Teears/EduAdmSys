package com.qifangli.edumanage.controller;

import com.alibaba.fastjson.JSONObject;
import com.qifangli.edumanage.dao.entity.CourseArrange;
import com.qifangli.edumanage.dao.entity.Spot;
import com.qifangli.edumanage.service.CourseArrangeService;
import com.qifangli.edumanage.service.SpotService;
import com.qifangli.edumanage.util.result.Result;
import com.qifangli.edumanage.util.result.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/spot")
public class RoomController {
    @Autowired
    private SpotService spotService;

    @Autowired
    private CourseArrangeService courseArrangeService;

    @PostMapping("getRoomByArea")
    public Result getRoomByArea(@RequestBody JSONObject param){
        String area = param.getString("area");
        List<String> room = spotService.findRoomByArea(area);
        return ResultUtils.success(room);
    }

    @PostMapping("getRoomTable")
    public Result getRoomTable(@RequestBody JSONObject param){
        String term = param.getString("term");
        String area = param.getString("area");
        String room = param.getString("room");
        Spot spot = spotService.findSpotByAreaAndRoom(area,room);
        List<CourseArrange> courseArrange = courseArrangeService.findByTermAndSpot(term,spot.getId());
        LinkedList<Map<String,String>> datas = new LinkedList<>();
        for(CourseArrange item : courseArrange){
            Map<String,String> data = new HashMap<>();
            data.put("week",item.getWeek());
            data.put("time",item.getTime());
            data.put("info",item.getCourseName() + "\n" + item.getTeacherName() + "["+item.getSelected()+"人]");
            datas.add(data);
        }
        return ResultUtils.success(datas);
    }
}
