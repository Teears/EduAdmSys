package com.qifangli.edumanage.util.shiro;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.qifangli.edumanage.filter.ShiroUserFilter;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

@Configuration
public class ShiroConfig {

    @Bean
    ShiroFilterFactoryBean shiroFilterFactoryBean() {
        System.out.println("shiroFilterFactoryBean run");
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();

        Map<String, Filter> filter = new HashMap<>();
        filter.put("user", new ShiroUserFilter());
        bean.setFilters(filter);

        bean.setSecurityManager(securityManager());
        bean.setLoginUrl("/unauthorized/noLogin");
        bean.setUnauthorizedUrl("/unauthorized/noPerm");
        Map<String, String> map = new LinkedHashMap<>();
        map.put("/login/**", "anon");
        map.put("/pwd/resetPwd", "anon");
        map.put("/phone/sendCode", "anon");
        map.put("/**", "user");
        bean.setFilterChainDefinitionMap(map);
        return bean;
    }

    @Bean
    DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(myRealm());
        return manager;
    }

    @Bean
    MyRealm myRealm() {
        return new MyRealm();
    }

    /**
     * 下面的代码是添加注解支持
     */

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }
}