package com.qifangli.edumanage.controller;

import com.alibaba.fastjson.JSONObject;
import com.qifangli.edumanage.dao.entity.Role;
import com.qifangli.edumanage.dao.entity.Student;
import com.qifangli.edumanage.service.RoleService;
import com.qifangli.edumanage.service.StudentService;
import com.qifangli.edumanage.service.TeacherService;
import com.qifangli.edumanage.util.JWTUtil;
import com.qifangli.edumanage.util.result.Result;
import com.qifangli.edumanage.util.result.ResultUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController()
@RequestMapping("/admin")
public class AdminController {
    @Resource
    private TeacherService teacherService;
    @Resource
    private StudentService studentService;
    @Resource
    private RoleService roleService;

    @RequiresPermissions("admin_stuAdmin")
    @PostMapping("/stuAdmin")
    public Result getStuInfo(HttpServletRequest request){
        String token = request.getHeader("token");
        String id = JWTUtil.getUsername(token);
        String dpt = teacherService.findTeacherById(id).getDepartment();
        List<Student> students = studentService.findStuByDpt(dpt);

        return ResultUtils.success(students);
    }

    @RequiresPermissions("admin_authorized")
    @PostMapping("/authorize")
    public Result authorize(@RequestBody JSONObject param,HttpServletRequest request){
        String token = request.getHeader("token");
        String id = JWTUtil.getUsername(token);
        List<Role> roles = roleService.findAllRole(id);
        return ResultUtils.success(roles);
    }

    @RequiresPermissions("admin_authorized")
    @PostMapping("/authorize/upToSuperAdmin")
    public Result updateToSuperAdmin(@RequestBody JSONObject param){
        String id = param.getString("id");
        roleService.updateToSuperAdminById(id);
        return ResultUtils.success();
    }

    @RequiresPermissions("admin_authorized")
    @PostMapping("/authorize/upToAdmin")
    public Result updateToAdmin(@RequestBody JSONObject param){
        String id = param.getString("id");
        roleService.updateToAdminById(id);
        return ResultUtils.success();
    }

    @RequiresPermissions("admin_authorized")
    @PostMapping("/authorize/deleteByUid")
    public Result deleteByUid(@RequestBody JSONObject param){
        String id = param.getString("id");
        roleService.deleteByUid(id);
        return ResultUtils.success();
    }

    @RequiresPermissions("admin_authorized")
    @PostMapping("/authorize/addAdmin")
    public Result addAdmin(@RequestBody JSONObject param){
        String uid = param.getString("uid");
        String rid = param.getString("name");
        Role role;
        try {
            roleService.addAdmin(uid,rid);
            role = roleService.findRoleByUserId(uid);
        }catch (DataAccessException e){
            e.printStackTrace();
            return ResultUtils.error(-1,"该教师已经是管理员了");
        }
        return ResultUtils.success(role);
    }


}
