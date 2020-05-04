package com.qifangli.edumanage.filter;

import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 跨域的设置问题
 * @author qifangli
 * @version v1.0.0
 */

//@Component   //在启动类加了@ServletComponentScan无需加这个了
@Order(3)//设置优先级加载
//@ServletComponentScan  //加载启动类上了
@WebFilter(urlPatterns = "/*",filterName = "ACAFilter")
public class ACAFilter implements Filter {

    /**
     * 初始化
     * @param filterConfig FilterConfig
     * @throws ServletException
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    /**
     * 过滤
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:8081");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", ":x-requested-with,content-type,token");
        response.setHeader("Access-Control-Allow-Credentials","true");
//        response.addCookie(new Cookie("JSSESIONID",request.getSession().getId()));

        //((HttpServletResponse)servletResponse).setHeader("Access-Control-Allow-Origin", "*");
        filterChain.doFilter(servletRequest,servletResponse);
        System.out.println("*********************************过滤器被使用**************************");    }

    /**
     * 销毁
     */
    @Override
    public void destroy() {
    }
}