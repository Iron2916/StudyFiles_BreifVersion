package com.iron.controller;

import com.iron.pojo.User;
import com.iron.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("test")
public class controller
{
    @Autowired
    TestService service;


    @GetMapping("/get")
    List<User> getMessage()
    {
        List<User> users = service.getUser();
        System.out.println(users);
        return users;
    }

    @GetMapping("/get/{id}")
    User getById(@PathVariable("id") int id)
    {
        return service.selectById(id);
    }
}
