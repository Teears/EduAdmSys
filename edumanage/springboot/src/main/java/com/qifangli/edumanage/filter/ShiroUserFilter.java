package com.qifangli.edumanage.filter;

import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.web.filter.authc.UserFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * @author Tears
 */
public class ShiroUserFilter extends UserFilter {
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        System.out.println("shirofilter run");
        if (httpRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            httpResponse.setHeader("Access-control-Allow-Origin", "http://localhost:81");
            httpResponse.setHeader("Access-Control-Allow-Methods", "POST,PUT, GET, OPTIONS, DELETE");
            httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
            httpResponse.setHeader("Access-Control-Max-Age", "3600");
            httpResponse.setHeader("Access-Control-Allow-Headers", ":x-requested-with,content-type,token");
            httpResponse.setStatus(HttpStatus.OK.value());
            return false;
        }
        if ("OPTIONS".equals(httpRequest.getMethod())) {
            httpResponse.setStatus(200);
            return false;
        }
        return super.preHandle(request,response);
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletResponse httpResp = WebUtils.toHttp(response);
        HttpServletRequest httpReq = WebUtils.toHttp(request);

        /*系统重定向会默认把请求头清空，这里通过拦截器重新设置请求头，解决跨域问题*/
        httpResp.setHeader("Access-control-Allow-Origin", "http://localhost:81");
        httpResp.setHeader("Access-Control-Allow-Methods", "POST,PUT, GET, OPTIONS, DELETE");
        httpResp.setHeader("Access-Control-Allow-Credentials", "true");
        httpResp.setHeader("Access-Control-Max-Age", "3600");
        httpResp.setHeader("Access-Control-Allow-Headers", ":x-requested-with,content-type,token");

        if ("OPTIONS".equals(httpReq.getMethod())) {
            httpResp.setStatus(200);
            return false;
        }

        this.saveRequestAndRedirectToLogin(request, response);
        return false;
    }
}
