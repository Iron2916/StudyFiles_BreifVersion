package com.iron.Demo01_IdealUseApi;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/ideal")
public class Controller {

    @GetMapping("/get")
    public Map getMethod() {

        final Map<String, String> map = new HashMap<>();
        map.put("name", "zhangsan");
        map.put("age", "18");

        return map;
    }

    @PostMapping("/post")
    public void postMethod(String name, int age) {

        System.out.println("name = " + name);
        System.out.println("age = " + age);
    }

    @PostMapping("/post2")
    public void post2Method(@RequestBody Student student) {

        System.out.println(student);
    }
}
