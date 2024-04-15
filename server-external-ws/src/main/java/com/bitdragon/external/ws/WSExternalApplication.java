package com.bitdragon.external.ws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "com.bitdragon.*")
@SpringBootApplication
public class WSExternalApplication {
    public static void main(String[] args) {
        SpringApplication.run(WSExternalApplication.class, args);
    }
}
