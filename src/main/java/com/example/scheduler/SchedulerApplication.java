package com.example.scheduler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableJpaAuditing
@EnableScheduling
public class SchedulerApplication {


//    static {
//        System.setProperty("spring.config.location", "classpath:/application.yml,classpath:/jwt.yml");
//    }


    public static void main(String[] args) {
        SpringApplication.run(SchedulerApplication.class, args);
    }

}
