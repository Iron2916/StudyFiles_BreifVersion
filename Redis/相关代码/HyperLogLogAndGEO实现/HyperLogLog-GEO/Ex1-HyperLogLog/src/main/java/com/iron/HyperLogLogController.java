package com.iron;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HyperLogLogController {

    @Autowired
    RedisTemplate redisTemplate;

    @GetMapping("get")
    public Long getUV() {

        return redisTemplate.opsForHyperLogLog().size("hll");
    }
}
