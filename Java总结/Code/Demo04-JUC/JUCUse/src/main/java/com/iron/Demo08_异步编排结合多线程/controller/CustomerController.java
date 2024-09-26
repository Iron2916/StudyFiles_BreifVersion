package com.iron.Demo08_异步编排结合多线程.controller;

import com.iron.Demo08_异步编排结合多线程.CustomerMixInfo;
import com.iron.Demo08_异步编排结合多线程.service.CustomerService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController
{
    @Resource
    private CustomerService customerSerivce;

    @GetMapping(value = "/customer/findv1")
    public CustomerMixInfo findCustomer()
    {
        return customerSerivce.findCustomer();
    }

    @GetMapping(value = "/customer/findv2")
    public CustomerMixInfo findCustomerByCompletableFuture()
    {
        return customerSerivce.findCustomerByCompletableFuture();
    }
}