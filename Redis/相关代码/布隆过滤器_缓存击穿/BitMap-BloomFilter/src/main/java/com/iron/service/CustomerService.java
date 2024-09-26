package com.iron.service;

import com.iron.dao.Customer;

public interface CustomerService {

    public void addCustomer(Customer customer);

    public Customer findCustomerById(Integer customreId);

    public Customer findCustomerByIdWithBloomFilter (Integer customerId);

}
