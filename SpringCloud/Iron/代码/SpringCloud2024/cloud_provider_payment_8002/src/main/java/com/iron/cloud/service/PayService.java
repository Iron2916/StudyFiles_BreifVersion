package com.iron.cloud.service;

import com.iron.cloud.pojo.Pay;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PayService {

    public int add(Pay pay);
    public int delete(Integer id);
    public int update(Pay pay);

    public Pay   getById(Integer id);
    public List<Pay> getAll();
}
