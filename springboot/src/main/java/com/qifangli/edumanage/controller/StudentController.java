package com.qifangli.edumanage.controller;

import com.alibaba.fastjson.JSONObject;
import com.qifangli.edumanage.dao.entity.CourseArrange;
import com.qifangli.edumanage.dao.entity.Score;
import com.qifangli.edumanage.dao.entity.Student;
import com.qifangli.edumanage.dao.entity.StudentScore;
import com.qifangli.edumanage.service.CourseArrangeService;
import com.qifangli.edumanage.service.ScoreService;
import com.qifangli.edumanage.service.StudentService;
import com.qifangli.edumanage.service.TermService;
import com.qifangli.edumanage.util.result.Result;
import com.qifangli.edumanage.util.result.ResultUtils;
import com.qifangli.edumanage.util.JWTUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RestController()
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private CourseArrangeService courseArrangeService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private TermService termService;

    @RequiresRoles(value={"student"})
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
            data.put("info",item.getCourseName() + "\n" + item.getTeacherName() + "\n" + item.getArea()+item.getRoom());
            datas.add(data);
        }
        return ResultUtils.success(datas);
    }

    @PostMapping("getScoreTable")
    public Result getScoreTable(@RequestBody JSONObject param, HttpServletRequest request){
        String token = request.getHeader("token");
        String id = JWTUtil.getUsername(token);

        String term = param.getString("term");
        List<StudentScore> studentScore = studentService.findScoreByTermAndStuId(term,id);

        return ResultUtils.success(studentScore);
    }

    @PostMapping("getInfoTable")
    public Result getInfoTable(HttpServletRequest request){
        String token = request.getHeader("token");
        String id = JWTUtil.getUsername(token);
        Student student = studentService.findStudentById(id);
        LinkedList<Map<String,String>> datas = new LinkedList<>();
        Map<String,String> data = new HashMap<>();
        data.put("id",student.getId());
        data.put("name",student.getName());
        data.put("sex",student.getSex());
        data.put("birth",student.getBirth());
        data.put("political",student.getPolitical());
        data.put("classAndGrade",student.getClassAndGrade());
        data.put("status",student.getStatus());
        data.put("all_credit",studentService.findSumCredit(id).toString());
        data.put("avg_grade", String.format("%.2f",studentService.findAvgScore(id)));
        datas.add(data);
        return ResultUtils.success(datas);
    }

    @RequiresRoles(value={"student"})
    @RequiresPermissions(value={"student:select"})
    @PostMapping("getNewCourseArrange")
    public Result getNewCourseArrange (HttpServletRequest request){
        String token = request.getHeader("token");
        String id = JWTUtil.getUsername(token);

        String term = termService.findLatestTerm().toString();
        String dpt = studentService.findStudentById(id).getDepartment();
        List<CourseArrange> newArrange = courseArrangeService.findByTermAndDpt(term,dpt);

        return ResultUtils.success(newArrange);
    }

    @PostMapping("getNewCourseArrange/addSelect")
    public Result addSelect (@RequestBody JSONObject param,HttpServletRequest request){
        String token = request.getHeader("token");
        String id = JWTUtil.getUsername(token);
        String teaCrsId = param.getString("id");
        try{
            studentService.addCrs(id,teaCrsId);
        }catch (DataAccessException e){
            return ResultUtils.error(-1,"重复选课");
        }
        try {
            studentService.updataSelectedAdd(teaCrsId);
        }catch (DataAccessException e){
            return ResultUtils.error(-2,"选课人数已满");
        }
        return ResultUtils.success();
    }

    @PostMapping("getNewCourseArrange/deleteSelect")
    public Result deleteSelect (@RequestBody JSONObject param,HttpServletRequest request){
        String token = request.getHeader("token");
        String id = JWTUtil.getUsername(token);
        String teaCrsId = param.getString("id");
        try{
            studentService.deleteCrs(id,teaCrsId);
        }catch (DataAccessException e){
            return ResultUtils.error(-1,"你没有选修该门课");
        }
        try {
            studentService.updataSelectedSub(teaCrsId);
        }catch (DataAccessException e){
            return ResultUtils.error(-2,"已到最大退课人数");
        }
        return ResultUtils.success();
    }
}
