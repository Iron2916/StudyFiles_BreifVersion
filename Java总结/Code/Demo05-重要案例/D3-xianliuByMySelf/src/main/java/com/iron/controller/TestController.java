package com.iron.controller;

import cn.hutool.core.util.IdUtil;
import com.iron.annotations.RedisLimitAnnotation;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/redis/limit/test")
    @RedisLimitAnnotation(key = "redisLimit", permitsPerSecond = 3, expire = 10, msg = "当前排队人数较多，请稍后再试！")
    public String redisLimit()
    {
        return "正常业务返回，订单流水："+ IdUtil.fastUUID();
    }
}
