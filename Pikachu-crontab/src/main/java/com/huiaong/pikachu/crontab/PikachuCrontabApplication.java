package com.huiaong.pikachu.crontab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PikachuCrontabApplication {

    public static void main(String[] args) {
        SpringApplication.run(PikachuCrontabApplication.class, args);
    }

}
