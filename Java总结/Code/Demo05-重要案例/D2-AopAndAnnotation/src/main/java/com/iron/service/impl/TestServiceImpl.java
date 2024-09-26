package com.iron.service.impl;

import com.iron.logAnnotation.LogAnnotation;
import com.iron.service.TestService;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {

    @Override
    @LogAnnotation
    public String Test(String name)
    {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("------------------------ Test业务方法已经执行 ------------------------------------");
        return "方法执行完成";
    }
}
