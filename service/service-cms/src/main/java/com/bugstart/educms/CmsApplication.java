package com.bugstart.educms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author bugstart
 * @create 2023-02-01 15:36
 */
@SpringBootApplication
@ComponentScan({"com.bugstart"})
@MapperScan("com.bugstart.educms.mapper")
public class CmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(CmsApplication.class,args);
    }

}
