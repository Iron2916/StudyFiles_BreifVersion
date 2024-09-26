package com.iron;

import io.swagger.annotations.ApiOperation;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@RestController
public class JHSTaskController {
    public  static final String JHS_KEY_A="jhs:a";
    public  static final String JHS_KEY_B="jhs:b";

    @Resource
    private RedisTemplate redisTemplate;


    @GetMapping(value = "/pruduct/findab")
    @ApiOperation("防止热点key突然失效，AB双缓存架构")
    public List<Product> getByAB() {

        ArrayList<Product> list = null;
        // 查询A缓存
        list = (ArrayList<Product>) redisTemplate.opsForList().range(JHS_KEY_A, 1, 10);

        if (list.isEmpty()) {
            // 查询B缓存
            list = (ArrayList<Product>) redisTemplate.opsForList().range(JHS_KEY_B, 1, 10);

            if (list.isEmpty()) {
                // 查询数据库
            }
        }
        return list;
    }
}
