package com.iron.controller;

import com.iron.service.RedisLockService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedisLockController {

    @Autowired
    RedisLockService redisLockService;


    @ApiOperation("扣减一个库存")
    @GetMapping("/sale")
    public void sale() {

        redisLockService.sale();
    }
}
