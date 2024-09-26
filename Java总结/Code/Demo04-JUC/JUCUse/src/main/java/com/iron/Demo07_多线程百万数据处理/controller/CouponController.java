package com.iron.Demo07_多线程百万数据处理.controller;

import com.iron.Demo07_多线程百万数据处理.service.CouponService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CouponController {

    @Resource
    CouponService couponService;

    @GetMapping("/batchCoupon")
    public String batchCoupon()
    {

        couponService.batchCoupon();
        return "发送中，请到后台输出中查看";
    }

    @GetMapping("/batchCouponByModel")
    public String batchCouponByMode()
    {
        couponService.batchCouponByModel();
        return "发送中，请到后台输出中查看";
    }
}
