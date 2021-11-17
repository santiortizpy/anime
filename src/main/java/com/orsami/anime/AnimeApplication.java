package com.orsami.anime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication(proxyBeanMethods = false)
public class AnimeApplication {

    public static void main(String[] args) {
        SpringApplication.run(AnimeApplication.class, args);
    }
}
