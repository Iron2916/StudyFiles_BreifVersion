package com.iron;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@SpringBootApplication
public class Start {
    @Resource
    private ThreadPoolTaskExecutor threadPool;

    @PostConstruct
    public void getThreadPoolConfig()
    {
        System.out.println("*******测试threadPool getCorePoolSize: "+threadPool.getCorePoolSize());
        System.out.println("*******测试threadPool getMaxPoolSize: "+threadPool.getMaxPoolSize());
        System.out.println("*******测试threadPool getQueueCapacity: "+threadPool.getQueueCapacity());
        System.out.println("*******测试threadPool getKeepAliveSeconds: "+threadPool.getKeepAliveSeconds());
    }

    public static void main(String[] args) {

        SpringApplication.run(Start.class, args);
    }
}
