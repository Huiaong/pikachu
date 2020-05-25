package com.huiaong.pikachu.item.impl;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableDubbo
@SpringBootApplication
@EnableTransactionManagement
public class PikachuItemApplication {

    public static void main(String[] args) {
        SpringApplication.run(PikachuItemApplication.class, args);
    }
}
