package com.qifangli.edumanage.util.shiro;

import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShiroConfig {

    @Bean
    ShiroFilterFactoryBean shiroFilterFactoryBean() {
        System.out.println("shiroFilterFactoryBean run");
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(securityManager());
        bean.setLoginUrl("/login/**");
        bean.setUnauthorizedUrl("/unauthorized");
        Map<String, String> map = new LinkedHashMap<>();
        map.put("/login/**", "anon");
        map.put("/student/**","roles[student]");
        map.put("/teacher/**","roles[teacher]");
        map.put("/admin/**","roles[admin,super_admin]");
        map.put("/**", "authc");
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