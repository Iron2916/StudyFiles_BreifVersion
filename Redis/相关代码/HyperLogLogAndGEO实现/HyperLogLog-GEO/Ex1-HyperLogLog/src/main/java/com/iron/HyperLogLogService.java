package com.iron;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;


import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 模拟天猫页面传入用户数据
 */
@Service
public class HyperLogLogService {

    @Autowired
    RedisTemplate redisTemplate;

    /**
     * 初始化注解
     */
    @PostConstruct
    public void init() {

        final Random random = new Random();


        new Thread(new Runnable() {
            @Override
            public void run() {

                for (int i = 0; i < 10000; i++) {
                    String ip = random.nextInt(256) + "." + random.nextInt(256) + "." + random.nextInt(256) + "." + random.nextInt(256);
                    final Long num = redisTemplate.opsForHyperLogLog().add("hll", ip);
                    System.out.println("存入了一次数据！");
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                }
            }

        }, "t1").start();

    }
}
