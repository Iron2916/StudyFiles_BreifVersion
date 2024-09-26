package com.iron.mylock;

import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;

import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 利用redis自己手写的分布式锁：参照了 setNX， syncronized可重入锁，LUA脚本实现原子性， 操作当前线程，，自动续期等等实现了分布式锁
 */
public class RedisDistributeLock implements Lock {

    private StringRedisTemplate stringRedisTemplate;
    private String lockName; // KEYS[1]
    private String uuidValue; //ARGV[1]
    private long expireTime;// ARGV[2]

    public RedisDistributeLock(StringRedisTemplate stringRedisTemplate, String lockName, String uuid, Long expireTime) {

        this.stringRedisTemplate = stringRedisTemplate;
        this.lockName = lockName;
        this.uuidValue = uuid + Thread.currentThread().getId();
        this.expireTime = expireTime;
    }


    // 上锁逻辑：转移到 tryLock中进行实现
    @Override
    public void lock() {

        tryLock();
    }

    @Override
    public boolean tryLock() {

        try {tryLock(-1L,TimeUnit.SECONDS);} catch (InterruptedException e) {e.printStackTrace();}
        return false;
    }

    // lock加锁 逻辑真正实现的地方
    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {

        if (time == -1) {

            // Lua 脚本代码：对应的lockname/锁不存在或者存在且为同一线程，对应的hash值加一返回true。存在不为同样一线程返回false;
            String script =
                    "if redis.call('exists',KEYS[1]) == 0 or redis.call('hexists',KEYS[1],ARGV[1]) == 1 then    " +
                            "redis.call('hincrby',KEYS[1],ARGV[1],1)    " +
                            "redis.call('expire',KEYS[1],ARGV[2])    " +
                            "return 1  " +
                            "else   " +
                            "return 0 " +
                            "end";

            System.out.println(
                    "当前锁：" + lockName + "  " +
                    "当前的线程/uuid:" + uuidValue
            );

            // 自旋拿取锁：如果没有拿取到锁就一直旋转阻塞状态。
            while (!stringRedisTemplate.execute (new DefaultRedisScript<>(script, Boolean.class), Arrays.asList(lockName), uuidValue, String.valueOf(expireTime))) {

                // 这里必须暂停一段时间，不然服务器会一直跑
                TimeUnit.MICROSECONDS.sleep(60);
            }

            // 自动续期
            renewExpire();
            return true;
        }
        return false;
    }

    // 另起一个定时任务进行监视：每当实现达到 1/3 就自动续期
    private void renewExpire() {

        String script =
                "if redis.call('HEXISTS',KEYS[1],ARGV[1]) == 1 then     " +
                        "return redis.call('expire',KEYS[1],ARGV[2]) " +
                        "else     " +
                            "return 0 " +
                        "end";

        new Timer().schedule(new TimerTask() {

            @Override
            public void run() {

                // 如果存在就进行续期，递归调用
                if (stringRedisTemplate.execute(new DefaultRedisScript<>(script, Boolean.class), Arrays.asList(lockName), uuidValue, String.valueOf(expireTime))) {

                    renewExpire();
                }
            }
        }, (this.expireTime * 1000 / 3));
    }

    @Override
    public void unlock() {

        // Lua脚本：不存在返回nill，存在且为当前线程hash值进行减一，存在且不为当前线程返回false
        String script =
                "if redis.call('HEXISTS',KEYS[1],ARGV[1]) == 0 then    " +
                        "return nil  " +
                        "elseif redis.call('HINCRBY',KEYS[1],ARGV[1],-1) == 0 then    " +
                        "return redis.call('del',KEYS[1])  " +
                        "else    " +
                        "return 0 " +
                        "end";

        // 1:true 0:false null:不存在
        Long flag = stringRedisTemplate.execute(new DefaultRedisScript<>(script, Long.class), Arrays.asList(lockName), uuidValue, String.valueOf(expireTime));

        if (flag == null) {

            throw new RuntimeException("出问题了，改锁不存在！");
        }

    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }



    @Override
    public Condition newCondition() {
        return null;
    }
}
