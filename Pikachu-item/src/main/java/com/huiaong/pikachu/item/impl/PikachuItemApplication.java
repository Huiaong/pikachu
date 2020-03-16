package com.huiaong.pikachu.item.impl;

import com.alibaba.dubbo.container.Main;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PikachuItemApplication {

    public static void main(String[] args) {
        SpringApplication.run(PikachuItemApplication.class, args);
        Main.main(args);
    }
}
