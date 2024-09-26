package com.iron.Demo07_多线程百万数据处理.service;

import org.springframework.stereotype.Service;


public interface CouponService {

    void  batchCoupon();

    void batchCouponByModel();
}
