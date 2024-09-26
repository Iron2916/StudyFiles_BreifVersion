package com.iorn.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import java.util.LinkedList;
import java.util.Queue;

@SpringBootApplication
@EnableDiscoveryClient
public class Main3377 {

    public static void main(String[] args) {

        Queue<Integer> queue = new LinkedList<>();
        queue.poll();
        SpringApplication.run(Main3377.class, args);
    }
}
