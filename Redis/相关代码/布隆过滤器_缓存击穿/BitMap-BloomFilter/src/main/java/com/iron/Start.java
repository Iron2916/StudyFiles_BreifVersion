package com.iron;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.iron.mapper") //import tk.mybatis.spring.annotation.MapperScan;
public class Start {

    public static void main(String[] args) {

        SpringApplication.run(Start.class, args);
    }
}
