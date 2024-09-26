package com.iron.cloud.controller;

import com.iron.cloud.pojo.Order;
import com.iron.cloud.response.ResultData;
import com.iron.cloud.service.OrderService;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.transform.Result;

@RestController
@CrossOrigin
public class OrderController {

    @Autowired
    OrderService orderService;

    @GetMapping("/order/create")
    public ResultData insertOrder(Order order) {

        orderService.create(order);

        return ResultData.success(order);
    }

}
