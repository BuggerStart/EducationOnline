package com.bugstart.staservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author bugstart
 * @create 2023-02-05 20:55
 */
@SpringBootApplication
@ComponentScan("com.bugstart")
@MapperScan("com.bugstart.staservice.mapper")
@EnableFeignClients
@EnableDiscoveryClient
@EnableScheduling
public class StaApplication {
    public static void main(String[] args) {
        SpringApplication.run(StaApplication.class,args);
    }
}
