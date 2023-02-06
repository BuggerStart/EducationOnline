package com.bugstart.educenter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author bugstart
 * @create 2023-02-02 14:48
 */
@SpringBootApplication
@MapperScan("com.bugstart.educenter.mapper")
@ComponentScan({"com.bugstart"})
public class UcenterApplication {
    public static void main(String[] args) {
        SpringApplication.run(UcenterApplication.class,args);
    }
}
