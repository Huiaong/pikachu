package com.huiaong.pikachu.user.impl;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableDubbo
@SpringBootApplication
@EnableTransactionManagement
public class PikachuUserApplication {
    public static void main(String[] args) {
        SpringApplication.run(PikachuUserApplication.class, args);
    }
}
