package org.example.zick;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ZiCkApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZiCkApplication.class, args);
    }
}
