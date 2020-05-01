package com.huiaong.pikachu.article.impl;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableDubbo
@SpringBootApplication
public class PikachuArticleApplication {

    public static void main(String[] args) {
        SpringApplication.run(PikachuArticleApplication.class, args);
    }
}
