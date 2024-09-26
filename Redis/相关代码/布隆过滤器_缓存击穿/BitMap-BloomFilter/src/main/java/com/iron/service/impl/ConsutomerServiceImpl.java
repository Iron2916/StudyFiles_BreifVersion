package com.iron.service.impl;

import com.iron.dao.Customer;
import com.iron.mapper.CustomerMapper;
import com.iron.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class ConsutomerServiceImpl implements CustomerService {

    private static final String CACHA_KEY_CUSTOMER = "customer:";
    @Resource
    CustomerMapper customerMapper;

    @Resource
    RedisTemplate redisTemplate;

    @Override
    public void addCustomer(Customer customer) {

        final int res = customerMapper.insertSelective(customer);

        if (res > 0) {
            // 插入成功将结果写入到redis中

            final Customer c = customerMapper.selectByPrimaryKey(customer.getId());
            String key = CACHA_KEY_CUSTOMER+c.getId();
            redisTemplate.opsForValue().set(key, c);
        }
    }

    @Override
    public Customer findCustomerById(Integer customreId) {

        String key = CACHA_KEY_CUSTOMER + customreId;
        Customer customer = (Customer) redisTemplate.opsForValue().get(key);

        if (customer == null) {
            // 查询数据库

            return customerMapper.selectByPrimaryKey(customreId);
        }
        return customer;
    }

    @Override
    public Customer findCustomerByIdWithBloomFilter(Integer customerId) {

        String key = CACHA_KEY_CUSTOMER + customerId;
        if (!checkByBloomFilter("whitelistCustomer", key)) {

            System.out.println("白名单无此顾客，不可以访问: " + key);
            return null;
        }

        Customer customer = null;
        customer = (Customer) redisTemplate.opsForValue().get(key);
        //redis无，进一步查询mysql
        if (customer == null) {
            //2 从mysql查出来customer
            customer = customerMapper.selectByPrimaryKey(customerId);
            // mysql有，redis无
            if (customer != null) {
                //3 把mysql捞到的数据写入redis，方便下次查询能redis命中。
                redisTemplate.opsForValue().set(key, customer);
            }
        }
        return customer;
    }

    public boolean checkByBloomFilter(String checkItem, String key)
    {

        final int hashValue = Math.abs(key.hashCode());

        long index = (long)(hashValue % Math.pow(2,32));    //哈希函数进行映射，这里的长度为 Math.pow(2,32)

        final Boolean res = redisTemplate.opsForValue().getBit(checkItem, index);   // redis中的 bitmap 查询是否存在该值

        System.out.println("--->key:" + key + " 对应坑位下标index: " + index + " 是否存在：" + res);
        return res;
    }
}
