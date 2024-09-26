package com.iron.service;

import com.iron.mylock.DistributedLockFactory;
import com.iron.mylock.RedisDistributeLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RedisLockService {


    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    DistributedLockFactory distributedLockFactory;
    public void sale() {


        // 在操作资源的时候必须枷锁
        RedisDistributeLock lock = (RedisDistributeLock) distributedLockFactory.getDistributedLock("redis");

        // 加锁
        lock.lock();

        try{

            final String result = stringRedisTemplate.opsForValue().get("TestRedisLock");
            int num = result == null ? 0 : Integer.valueOf(result);

            if (num > 0) {

                stringRedisTemplate.opsForValue().set("TestRedisLock", String.valueOf(--num));
            } else {

                System.out.println("商品卖完了！");
            }

        } finally {

            // 释放锁
            lock.unlock();
        }


    }
}
