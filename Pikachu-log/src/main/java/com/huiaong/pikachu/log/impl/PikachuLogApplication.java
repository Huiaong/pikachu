package com.huiaong.pikachu.log.impl;

import com.alibaba.dubbo.container.Main;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PikachuLogApplication {

    public static void main(String[] args) {
        SpringApplication.run(PikachuLogApplication.class, args);
        Main.main(args);
    }
}
