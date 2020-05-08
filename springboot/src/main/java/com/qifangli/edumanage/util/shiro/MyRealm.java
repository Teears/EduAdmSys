package com.qifangli.edumanage.util.shiro;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.qifangli.edumanage.dao.entity.Permission;
import com.qifangli.edumanage.dao.entity.Role;
import com.qifangli.edumanage.dao.entity.Teacher;
import com.qifangli.edumanage.dao.mapper.PermissionMapper;
import com.qifangli.edumanage.service.RoleService;
import com.qifangli.edumanage.service.TeacherService;
import com.qifangli.edumanage.util.UserUtil;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import javax.annotation.Resource;

import com.qifangli.edumanage.util.JWTUtil;


public class MyRealm extends AuthorizingRealm {

    @Resource
    private TeacherService teacherService;
    @Resource
    private Teacher teacher;
    @Resource
    private Role role;
    @Resource
    private RoleService roleService;
    @Resource
    private PermissionMapper permissionMapper;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        String username = JWTUtil.getUsername(principals.toString());

        teacher = teacherService.findTeacherById(username);
        role = roleService.findRoleByUserId(teacher.getId());
        simpleAuthorizationInfo.addRole(role.getRole());

        List<Permission> permissions = permissionMapper.findPermsByRid(role.getRid());
        Set<String> perms = new HashSet<>();
        for(Permission item: permissions){
            perms.add(item.getPermsCode());
        }
        simpleAuthorizationInfo.addStringPermissions(perms);

        return simpleAuthorizationInfo;
    }

    /**
     * 默认使用此方法进行用户正确与否验证，错误抛出异常即可
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String token = (String) authenticationToken.getCredentials();
        // 解密获得username，用于和数据库进行对比
        String username = JWTUtil.getUsername(token);
        if (username == null) {
            throw new AuthenticationException("token 无效！");
        }

        String pwd = UserUtil.getPwdById(username);
        if (pwd == null) {
            throw new AuthenticationException("用户"+username+"不存在") ;
        }

        if (!JWTUtil.verify(token, username, pwd)) {
            throw new AuthenticationException("账户密码错误!");
        }
        return new SimpleAuthenticationInfo(token, token, "myRealm");
    }

}
