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
        registry.addViewController("/").setViewName("/rems-front/login");
        registry.addViewController("/login.html").setViewName("/rems-front/login");
        // 注册页面映射
        registry.addViewController("/toRegister").setViewName("/rems-front/register");
        registry.addViewController("/back").setViewName("/rems-back/index");
        registry.addViewController("/iframe").setViewName("/rems-back/rems-core/iframe");
        registry.addViewController("/course01").setViewName("/rems-back/rems-course-learning-manage/course-learning-01");
        registry.addViewController("/course02").setViewName("/rems-back/rems-course-learning-manage/course-learning-02");
        registry.addViewController("/course03").setViewName("/rems-back/rems-course-learning-manage/course-learning-03");
        registry.addViewController("/course04").setViewName("/rems-back/rems-course-learning-manage/course-learning-04");
        registry.addViewController("/rems-user-manage-list").setViewName("/rems-back/rems-user-manage/rems-user-manage-list");
        registry.addViewController("/rems-course-learning-manage-list").setViewName("/rems-back/rems-course-learning-manage/rems-course-learning-manage-list");
        registry.addViewController("/rems-question-manage-list").setViewName("/rems-back/rems-question-manage/rems-question-manage-list");
        registry.addViewController("/rems-popular-nav-manage-list").setViewName("/rems-back/rems-popular-nav-manage/rems-popular-nav-manage-list");
        registry.addViewController("/rems-message-manage-list").setViewName("/rems-back/rems-message-manage/rems-message-manage-list");


        registry.addViewController("/course-learning").setViewName("rems-back/rems-course-learning-manage/course-learning");

    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
            // 拦截规则 ，拦截那些路径
            .addPathPatterns("/**")
            // 那些路径不拦截
            .excludePathPatterns(
                    "/",
                    "/login",
                    "/index",
                    "/toRegister",
                    "/register",
                    "/static/**",
                    "/css/**",
                    "/images/**",
                    "/js/**",
                    "/layui/**",
                    "/jquery/**",
                    "/bootstrap/**",
                    "/**/**/*.html");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        WebMvcConfigurer.super.addResourceHandlers(registry);
    }
}
