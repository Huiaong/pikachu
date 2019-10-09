package com.huiaong.pikachu.trade.impl;

import com.alibaba.dubbo.container.Main;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PikachuTradeApplication {

    public static void main(String[] args) {
        SpringApplication.run(PikachuTradeApplication.class, args);
        Main.main(args);
    }

}
