package com.huiaong.pikachu.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PikachuAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(PikachuAdminApplication.class, args);
    }

}
