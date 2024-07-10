package com.rems.boot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Author qinj
 * @Date 2024/7/5
 * @Description rems 启动类
 * @Version 1.0
 */
@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.rems.boot.mapper")
public class RemsBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(RemsBootApplication.class, args);
    }

}
