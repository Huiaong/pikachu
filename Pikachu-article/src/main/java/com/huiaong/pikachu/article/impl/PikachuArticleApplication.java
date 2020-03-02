package com.huiaong.pikachu.article.impl;

import com.alibaba.dubbo.container.Main;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PikachuArticleApplication {

    public static void main(String[] args) {
        SpringApplication.run(PikachuArticleApplication.class, args);
        Main.main(args);
    }
}
