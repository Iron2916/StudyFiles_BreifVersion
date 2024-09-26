package com.iron.controller;

import com.iron.service.TestService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Resource
    TestService testService;

    @GetMapping("/Test")
    public void testService()
    {
        testService.Test();
    }
}
