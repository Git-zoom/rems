package com.rems.boot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.rems.boot.mapper")
public class RemsBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(RemsBootApplication.class, args);
    }

}
