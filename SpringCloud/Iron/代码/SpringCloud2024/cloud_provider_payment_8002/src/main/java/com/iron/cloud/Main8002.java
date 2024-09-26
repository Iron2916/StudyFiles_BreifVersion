package com.iron.cloud;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.iron.cloud.mapper")    // 设置mapper扫描包，mapper不用加注解,这里应该导入 tk 的包
@EnableDiscoveryClient  // consul 服务注册发现
public class Main8002 {

    public static void main(String[] args) {

        SpringApplication.run(Main8002.class, args);
    }
}
