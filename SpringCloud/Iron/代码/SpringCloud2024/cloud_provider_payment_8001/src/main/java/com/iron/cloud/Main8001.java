package com.iron.cloud;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import tk.mybatis.spring.annotation.MapperScan;

@MapperScan("com.iron.cloud.mapper")    // 设置mapper扫描包，mapper不用加注解,这里应该导入 tk 的包
@SpringBootApplication
@EnableDiscoveryClient  // spring-consul 注解
public class Main8001 {

    public static void main(String[] args) {

        SpringApplication.run(Main8001.class, args);
    }
}
