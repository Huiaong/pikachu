package com.huiaong.pikachu.resource.impl;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableDubbo
@SpringBootApplication
public class PikachuResourceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PikachuResourceApplication.class, args);
    }

}
