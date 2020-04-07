package com.lsc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class AppserverConfig {

    public static void main(String[] args) {
        SpringApplication.run(AppserverConfig.class,args);
    }
}
