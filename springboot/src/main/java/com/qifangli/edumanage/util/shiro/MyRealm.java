package com.qifangli.edumanage.util.shiro;

import com.qifangli.edumanage.dao.entity.Teacher;
import com.qifangli.edumanage.service.PermissionService;
import com.qifangli.edumanage.service.StudentService;
import com.qifangli.edumanage.dao.entity.Permission;
import com.qifangli.edumanage.dao.entity.Role;
import com.qifangli.edumanage.dao.entity.Student;
import com.qifangli.edumanage.service.RoleService;
import com.qifangli.edumanage.service.TeacherService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import javax.annotation.Resource;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MyRealm extends AuthorizingRealm {

    @Resource
    private TeacherService teacherService;
    @Resource
    private RoleService roleService;
    @Resource
    private PermissionService permissionService;
    @Resource
    private StudentService studentService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        String username = principals.toString();
        System.out.println("授权 run");
        List<Permission> permissions = null;
        int tlog = 8;
        int slog = 10;
        if(username==null){
            return null;
        }
        if(username.length()==slog){
            simpleAuthorizationInfo.addRole("student");
            permissions = permissionService.findPermsByRid("student");
        }else if(username.length()==tlog){
            Teacher teacher = teacherService.findTeacherById(username);
            Role role = roleService.findRoleByUserId(teacher.getId());
            if(role==null){
                simpleAuthorizationInfo.addRole("teacher");
                permissions = permissionService.findPermsByRid("teacher");
            }else {
                simpleAuthorizationInfo.addRole(role.getRid());
                permissions = permissionService.findPermsByRid(role.getRid());
            }
        }else {
            return null;
        }
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
        String username = (String)authenticationToken.getPrincipal();
        String password = new String((char[])authenticationToken.getCredentials());
        // 解密获得username，用于和数据库进行对比
        System.out.println("认证 run");
        int tlog = 8;
        int slog = 10;
        if (username == null) {
            throw new AuthenticationException("token 无效！");
        }
        String pwd = null;
        if(username.length()==slog){
            Student student = studentService.findStudentById(username);
            pwd = student.getPass();
        }else if(username.length()==tlog){
            Teacher teacher = teacherService.findTeacherById(username);
            pwd = teacher.getPass();
        }
        if (pwd == null) {
            throw new AuthenticationException("用户"+username+"不存在") ;
        }
        if (!pwd.equals(password)){
            throw new AuthenticationException(username+"密码错误") ;
        }
        return new SimpleAuthenticationInfo(username, pwd, "myRealm");
    }

//    @Override
//    public boolean supports(AuthenticationToken token) {
//        return token instanceof JWTToken;
//    }

}
