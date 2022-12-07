package com.adminjava;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.adminjava.mapper")
public class AdminJavaApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminJavaApplication.class, args);
    }

}
