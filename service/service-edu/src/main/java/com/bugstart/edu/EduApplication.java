package com.bugstart.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author bugstart
 * @create 2023-01-25 21:30
 */
@EnableFeignClients
@SpringBootApplication()
@EnableDiscoveryClient
@ComponentScan(basePackages = {"com.bugstart"})
public class EduApplication {
    public static void main(String[] args) {
        SpringApplication.run(EduApplication.class,args);
    }
}
