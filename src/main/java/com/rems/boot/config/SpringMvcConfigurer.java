package com.rems.boot.config;

import com.rems.boot.config.interceptors.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author qinj
 * @Date 2022/8/6 11:14
 * @Description SpringMVC配置
 * @Version 1.0
 */
@Configuration
public class SpringMvcConfigurer implements WebMvcConfigurer {

    @Autowired
    LoginInterceptor loginInterceptor;

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // 登录页面映射
        registry.addViewController("/").setViewName("login");
        registry.addViewController("/login.html").setViewName("login");
        // 注册页面映射
        registry.addViewController("/toRegister").setViewName("red-page/register");
        registry.addViewController("/htgl").setViewName("red-page/htgl");
        registry.addViewController("/iframe").setViewName("red-page/iframe");
        registry.addViewController("/course01").setViewName("red-page/cl_01");
        registry.addViewController("/course02").setViewName("red-page/cl_02");
        registry.addViewController("/course03").setViewName("red-page/cl_03");
        registry.addViewController("/course04").setViewName("red-page/cl_04");
        registry.addViewController("/red-user-manage-list").setViewName("red-user-manage/red-user-manage-list");
        registry.addViewController("/red-course-learning-list").setViewName("red-course-learning/red-course-learning-list");
        registry.addViewController("/courseLearning").setViewName("red-page/courseLearning");
        registry.addViewController("/red-question-query-list").setViewName("red-question-query/red-question-query-list");
        registry.addViewController("/red-nav-manage-list").setViewName("red-nav-manage/red-nav-manage-list");
        registry.addViewController("/red-website-message-list").setViewName("red-website-message/red-website-message-list");

    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                // 拦截规则 ，拦截那些路径
                .addPathPatterns("/**")
                // 那些路径不拦截
                .excludePathPatterns("/", "/login", "/index","/toRegister","/register",
                        "/static/**", "/css/**", "/images/**","/js/**","/layui/**","/**/**/*.html");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        WebMvcConfigurer.super.addResourceHandlers(registry);
    }
}
