package com.qifangli.edumanage.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qifangli.edumanage.dao.entity.CourseArrange;
import com.qifangli.edumanage.dao.entity.Score;
import com.qifangli.edumanage.dao.entity.Teacher;
import com.qifangli.edumanage.service.CourseArrangeService;
import com.qifangli.edumanage.service.ScoreService;
import com.qifangli.edumanage.service.TeacherService;
import com.qifangli.edumanage.util.JWTUtil;
import com.qifangli.edumanage.util.LoggerUtil;
import com.qifangli.edumanage.util.WeekTimeUtil;
import com.qifangli.edumanage.util.result.Result;
import com.qifangli.edumanage.util.result.ResultUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RestController()
@RequestMapping("/teacher")
public class TeacherController {

    @Resource
    private TeacherService teacherService;
    @Resource
    private CourseArrangeService courseArrangeService;
    @Resource
    private ScoreService scoreService;

    /**
     * 获取某门选课的所有学生列表
     * @param param
     * @return
     */
    @RequiresPermissions("teacher_all")
    @PostMapping("getScoreData")
    public Result getScoreData(@RequestBody JSONObject param){
        String tcno = param.getString("id");
        List<Score> scores = scoreService.findScoreByTC(tcno);
        return ResultUtils.success(scores);
    }

    /**
     * 获取这个老师某学期教的课程
     * @param param
     * @param request
     * @return
     */
    @RequiresPermissions("teacher_all")
    @PostMapping("getCourse")
    public Result getClass(@RequestBody JSONObject param, HttpServletRequest request) {
        String token = request.getHeader("token");
        String term = param.getString("term");
        String id = JWTUtil.getUsername(token);
        List<CourseArrange> courseArranges = courseArrangeService.findByTermAndTeaId(term,id);
        LinkedList<Map<String,String>> datas = new LinkedList<>();
        for(CourseArrange item : courseArranges){
            Map<String,String> data = new HashMap<>();
            String time = WeekTimeUtil.convertTime(item.getTime());
            String week = WeekTimeUtil.convertWeek(item.getWeek());
            data.put("name",item.getCourseName()+"("+week+time+")");
            data.put("id",item.getId());
            datas.add(data);
        }
        return ResultUtils.success(datas);
    }

    /**
     * 提交成绩
     * @param param
     * @return
     */
    @RequiresPermissions("teacher_all")
    @PostMapping("postScoreList")
    public Result postScoreList(@RequestBody JSONObject param, HttpServletRequest request){
        String token = request.getHeader("token");
        String id = JWTUtil.getUsername(token);
        List<Score> scoreList = JSONArray.parseArray(param.getString("scoreList"),Score.class);
        int success = scoreService.updateScoreList(scoreList);
        String msg = "- 更新成绩id:"+id;
        LoggerUtil.info(msg);
        return ResultUtils.success(success);
    }

    /**
     * 获取教师个人信息
     * @param request
     * @return
     */
    @RequiresPermissions("teacher_all")
    @PostMapping("getInfoTable")
    public Result getInfoTable(HttpServletRequest request) {
        String token = request.getHeader("token");
        String id = JWTUtil.getUsername(token);
        LinkedList<Map<String,String>> datas = new LinkedList<>();
        Map<String,String> data = new HashMap<>();
        Teacher teacher = teacherService.findTeacherById(id);
        data.put("id",teacher.getId());
        data.put("name",teacher.getName());
        data.put("sex",teacher.getSex());
        data.put("tel",teacher.getTelephone());
        data.put("pol",teacher.getPolitical());
        data.put("title",teacher.getTitle());
        data.put("dpt",teacher.getDepartment());
        data.put("degree",teacher.getDegree());
        data.put("workTime",teacher.getWorkTime());
        datas.add(data);
        return ResultUtils.success(datas);
    }

    /**
     * 获取本学期教学任务
     * @param request
     * @return
     */
    @RequiresPermissions("teacher_all")
    @PostMapping("getTaskTable")
    public Result getTaskTable(HttpServletRequest request) {
        String token = request.getHeader("token");
        String id = JWTUtil.getUsername(token);
        String term;
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        if(month>7){
            term = year + "2";
        }else {
            term = year + "1";
        }
        List<CourseArrange> courseArranges = courseArrangeService.findByTermAndTeaId(term,id);
        LinkedList<Map<String,String>> datas = new LinkedList<>();
        for(CourseArrange item : courseArranges){
            Map<String,String> data = new HashMap<>();
            data.put("courseno",item.getCourseno());
            data.put("crsname",item.getCourseName());
            String time = WeekTimeUtil.convertTime(item.getTime());
            String week = WeekTimeUtil.convertWeek(item.getWeek());
            data.put("time",week+time);
            data.put("port",item.getArea()+item.getRoom());
            data.put("info","年级："+ item.getGrade()+"\n选课人数:"+item.getSelected()+
                    "\n课程类型："+item.getType()+"\n学时："+item.getHours()+"学分："+item.getCredit());
            datas.add(data);
        }
        return ResultUtils.success(datas);
    }

}
