package com.qifangli.edumanage.controller;

import com.alibaba.fastjson.JSONObject;
import com.qifangli.edumanage.dao.entity.Role;
import com.qifangli.edumanage.util.JWTUtil;
import com.qifangli.edumanage.util.LoggerUtil;
import com.qifangli.edumanage.util.result.Result;
import com.qifangli.edumanage.util.result.ResultUtils;
import com.qifangli.edumanage.service.RoleService;
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
@RequestMapping("/adminAuthorize")
public class AdminAuthorizeController {

    @Resource
    private RoleService roleService;

    /**
     * 获取有管理员权限的教师的，角色列表
     * @param request
     * @return
     */
    @RequiresPermissions("admin_authorized")
    @PostMapping("/authorize")
    public Result authorize(HttpServletRequest request){
        String token = request.getHeader("token");
        String id = JWTUtil.getUsername(token);
        List<Role> roles = roleService.findAllRole(id);
        return ResultUtils.success(roles);
    }

    /**
     * 升级成超级管理员
     * @param param
     * @return
     */
    @RequiresPermissions("admin_authorized")
    @PostMapping("/authorize/upToSuperAdmin")
    public Result updateToSuperAdmin(@RequestBody JSONObject param,HttpServletRequest request){
        String token = request.getHeader("token");
        String operator = JWTUtil.getUsername(token);
        String id = param.getString("id");
        roleService.updateToSuperAdminById(id);
        String msg = "- 将id:"+id+"升级成超级管理员operator:"+operator;
        LoggerUtil.warn(msg);
        return ResultUtils.success();
    }

    /**
     * 降为一般管理员
     * @param param
     * @return
     */
    @RequiresPermissions("admin_authorized")
    @PostMapping("/authorize/upToAdmin")
    public Result updateToAdmin(@RequestBody JSONObject param,HttpServletRequest request){
        String token = request.getHeader("token");
        String operator = JWTUtil.getUsername(token);
        String id = param.getString("id");
        roleService.updateToAdminById(id);
        String msg = "- 将id:"+id+"降为普通管理员operator:"+operator;
        LoggerUtil.warn(msg);
        return ResultUtils.success();
    }

    /**
     * 把某位教师移除管理员队列
     * @param param
     * @return
     */
    @RequiresPermissions("admin_authorized")
    @PostMapping("/authorize/deleteByUid")
    public Result deleteByUid(@RequestBody JSONObject param,HttpServletRequest request){
        String token = request.getHeader("token");
        String operator = JWTUtil.getUsername(token);
        String id = param.getString("id");
        roleService.deleteByUid(id);
        String msg = "- 将id:"+id+"从管理员移除operator:"+operator;
        LoggerUtil.warn(msg);
        return ResultUtils.success();
    }

    /**
     * 把某位教师添加到管理员队列
     * @param param
     * @return
     */
    @RequiresPermissions("admin_authorized")
    @PostMapping("/authorize/addAdmin")
    public Result addAdmin(@RequestBody JSONObject param,HttpServletRequest request){
        String token = request.getHeader("token");
        String operator = JWTUtil.getUsername(token);
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
        String msg = "- 将id:"+uid+"添加到管理员移除operator:"+operator;
        LoggerUtil.warn(msg);
        return ResultUtils.success(role);
    }
}
