package com.iron.mylock;

import cn.hutool.core.util.IdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.locks.Lock;

@Component
public class DistributedLockFactory
{
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    private String lockName;
    private String uuid;

    public DistributedLockFactory()
    {
        this.uuid = IdUtil.simpleUUID();
    }

    public Lock getDistributedLock(String lockType)
    {
        if(lockType == null) return null;

        if(lockType.equalsIgnoreCase("REDIS")){
            this.lockName = "RedisLock";
            return new RedisDistributeLock(stringRedisTemplate,lockName,uuid, 30L);
        }else if(lockType.equalsIgnoreCase("ZOOKEEPER")){
            this.lockName = "ZookeeperLockNode";
            //TODO zookeeper版本的分布式锁
            return null;
        }else if(lockType.equalsIgnoreCase("MYSQL")){
            //TODO MYSQL版本的分布式锁
            return null;
        }

        return null;
    }
}