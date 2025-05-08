package com.qifangli.edumanage.filter;

import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Order(3)//设置优先级加载
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
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:81");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", ":x-requested-with,content-type,token");
        response.setHeader("Access-Control-Allow-Credentials","true");

        if ("OPTIONS".equals(request.getMethod())) {
            response.setStatus(200);
            return;
        }

        filterChain.doFilter(servletRequest,servletResponse);
        System.out.println("*********************************过滤器被使用**************************");    }

    /**
     * 销毁
     */
    @Override
    public void destroy() {
    }
}