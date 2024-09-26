package com.iron.service.impl;

import com.iron.service.TestService;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {

    @Override
    public void Test()
    {
        //int b = 1 / 0;
        System.out.println("------------------------ Test业务方法已经执行 ------------------------------------");
    }
}
