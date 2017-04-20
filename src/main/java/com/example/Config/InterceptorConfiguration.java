package com.example.Config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by iam24 on 17/4/11.
 */
@Component
public class InterceptorConfiguration extends WebMvcConfigurerAdapter {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册拦截器
        InterceptorRegistration login = registry.addInterceptor(new LoginInterceptor());
        InterceptorRegistration admin = registry.addInterceptor(new AdminInterceptor());
        // 配置拦截的路径
        login.addPathPatterns("/user/**");
        login.addPathPatterns("/admin/**");
        admin.addPathPatterns("/admin/**");
        // 配置不拦截的路径
        login.excludePathPatterns("/**.html");

        // 还可以在这里注册其它的拦截器
        //registry.addInterceptor(new OtherInterceptor()).addPathPatterns("/**");
    }
}