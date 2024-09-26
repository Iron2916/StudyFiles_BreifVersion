package com.iron.service.impl;


import com.iron.annotation.MyRedisCache;
import com.iron.mapper.TestMapper;
import com.iron.pojo.User;
import com.iron.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceImpl implements TestService
{
    @Autowired
    TestMapper testMapper;

    @Override
    public List<User> getUser()
    {
        return testMapper.getUser();
    }
    @MyRedisCache(keyPrefix = "user", matchValue = "#id")   // 根据此传入的值拼接 redis的key
    @Override
    public User selectById(int id)
    {
        return testMapper.selectById(id);
    }

}
