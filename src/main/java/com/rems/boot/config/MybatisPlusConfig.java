package com.rems.boot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;

/**
 * @Author qinj
 * @Date 2024/7/8
 * @Description MybatisPlus 配置类
 * @Version 1.0
 */
@Configuration
public class MybatisPlusConfig {

    @Bean
    public MybatisPlusInterceptor paginationInterceptor() {
        MybatisPlusInterceptor page = new MybatisPlusInterceptor();
        page.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return page;
    }

}
