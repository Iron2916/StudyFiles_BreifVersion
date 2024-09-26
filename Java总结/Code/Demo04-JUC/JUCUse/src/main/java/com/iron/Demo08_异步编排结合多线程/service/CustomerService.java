package com.iron.Demo08_异步编排结合多线程.service;

import com.iron.Demo08_异步编排结合多线程.CustomerMixInfo;

public interface CustomerService
{
    CustomerMixInfo findCustomer();

    public CustomerMixInfo findCustomerByCompletableFuture ();
}