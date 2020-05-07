package com.qifangli.edumanage.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import com.qifangli.edumanage.util.shiro.JWTUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@WebFilter(urlPatterns = { "/*" }, filterName = "tokenAuthorFilter")
public class TokenAuthorFilter implements Filter {

    private static Logger logger = LoggerFactory.getLogger(TokenAuthorFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;

        String token = req.getHeader("token");
        String id = JWTUtil.getUsername(token);

        logger.info("token：{}", token);
        if (null == token || token.isEmpty()) {
            throw new ServletException("用户授权认证没有通过!客户端请求参数中无token信息");
        } else {
            if (JWTUtil.isTimeout()) {
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
