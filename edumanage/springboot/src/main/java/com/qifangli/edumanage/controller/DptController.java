package com.qifangli.edumanage.controller;

import com.qifangli.edumanage.dao.entity.Department;
import com.qifangli.edumanage.service.DepartmentService;
import com.qifangli.edumanage.service.TeacherService;
import com.qifangli.edumanage.util.JWTUtil;
import com.qifangli.edumanage.util.result.Result;
import com.qifangli.edumanage.util.result.ResultUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


@RestController()
@RequestMapping("/dpt")
public class DptController {

    @Resource
    private DepartmentService departmentService;
    @Resource
    private TeacherService teacherService;

    @PostMapping("/getDptName")
    public Result getDptName(HttpServletRequest request){
        String token = request.getHeader("token");
        String id = JWTUtil.getUsername(token);
        String dpt = teacherService.findTeacherById(id).getDepartment();
        LinkedList<Map<String,String>> datas = new LinkedList<>();

        Subject subject = SecurityUtils.getSubject();

        if(subject.hasRole( "super_admin" )){
            List<Department> departments = departmentService.findAllDpt();
            return ResultUtils.success(departments);
        }else if(subject.hasRole( "admin" )){
            Department department = departmentService.findDptByid(dpt);
            List<Department> departments = new LinkedList<>();
            departments.add(department);
            return ResultUtils.success(departments);
        }else {
            return ResultUtils.error(-1,"no authorized");
        }
    }
    @PostMapping("/getOwnDpt")
    public Result getOwnDpt(HttpServletRequest request){
        String token = request.getHeader("token");
        String id = JWTUtil.getUsername(token);
        String dpt = teacherService.findTeacherById(id).getDepartment();

        Department department = departmentService.findDptByid(dpt);
        return ResultUtils.success(department);
    }
}
