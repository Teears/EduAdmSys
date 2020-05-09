package com.qifangli.edumanage.filter;

import java.io.IOException;
import javax.annotation.Resource;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import com.qifangli.edumanage.service.StudentService;
import com.qifangli.edumanage.service.TeacherService;
import com.qifangli.edumanage.util.JWTUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@WebFilter(urlPatterns = { "/*" }, filterName = "tokenAuthorFilter")
public class TokenFilter implements Filter {

    private static Logger logger = LoggerFactory.getLogger(TokenFilter.class);
    @Resource
    private StudentService studentService;
    @Resource
    private TeacherService teacherService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;

        String token = req.getHeader("token");
        String id = JWTUtil.getUsername(token);
        String secret = null;
        Integer TLOG = 8;
        if(id.length()== TLOG){
            secret = teacherService.findTeacherById(id).getPass();
        }else {
            secret = studentService.findStudentById(id).getPass();
        }

        if (null == token || token.isEmpty()) {
            throw new ServletException("用户授权认证没有通过!客户端请求参数中无token信息");
        } else {
            if (JWTUtil.isTimeout(token,secret)) {
                //通过校验
                logger.info("token过滤ok!");
                chain.doFilter(request, response);
            } else {
                throw new ServletException("用户授权认证没有通过!客户端请求参数token信息无效");
            }
        }
    }
    @Override
    public void destroy() {}
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}
}
