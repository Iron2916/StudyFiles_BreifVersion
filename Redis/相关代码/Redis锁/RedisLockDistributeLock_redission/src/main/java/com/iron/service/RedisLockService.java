package com.iron.service;

import org.redisson.Redisson;
import org.redisson.RedissonMultiLock;
import org.redisson.api.RLock;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedisLockService {


    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    @Qualifier("redissonClient1")
    private RedissonClient redissonClient1;

    @Autowired
    @Qualifier("redissonClient2")
    private RedissonClient redissonClient2;

    @Autowired
    @Qualifier("redissonClient3")
    private RedissonClient redissonClient3;

    public void sale() {

        final RLock lock1 = redissonClient1.getLock("lock");
        final RLock lock2 = redissonClient2.getLock("lock");
        final RLock lock3 = redissonClient3.getLock("lock");
        // 加锁
        final RLock lock = new RedissonMultiLock(lock1,lock2, lock3);
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

            lock.unlock();
        }


    }
}
