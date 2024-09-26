package com.iron.service;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedisLockService {


    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    Redisson redisson;
    public void sale() {


        // 加锁
        final RLock lock = redisson.getLock("RedissonLock");
        lock.lock(12, TimeUnit.SECONDS);

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
            if (lock.isLocked() && lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }


    }
}
