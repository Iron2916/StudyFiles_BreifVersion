package com.iron.cloud.controller;

import cn.hutool.core.date.DateUtil;
import com.iron.cloud.apis.PayFeignApi;
import com.iron.cloud.pojo.Pay;
import com.iron.cloud.response.ResultData;
import com.iron.cloud.response.ReturnCodeEnum;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name="用户订单模块", description = "用户操作模块")
@RestController
public class OrderController {

    @Autowired
    PayFeignApi payFeignApi;

    @PostMapping("/consumer/add")
    @Operation(summary = "支付订单", description = "订单进行支付")
    public ResultData addOrder(@RequestBody Pay pay){


        return payFeignApi.addPay(pay);
    }

    @GetMapping("/consumer/get/{id}")
    @Operation(summary = "得到订单信息", description = "用户获得订单信息")
    public ResultData getPayInfo(@PathVariable("id") Integer id){

        try{

            System.out.println("调用开始时间：" + DateUtil.now());
            final ResultData<Pay> result = payFeignApi.getById(id);
            return result;
        } catch (Exception e) {

            e.printStackTrace();
            System.out.println("调用结束时间：" + DateUtil.now());
            return ResultData.fail(ReturnCodeEnum.RC500.getCode(), e.getMessage());
        }

//        return payFeignApi.getById(id);
    }

    @DeleteMapping("/consumer/del/{id}")
    @Operation(summary = "删除订单", description = "用户删除订单信息")
    public ResultData deletePay(@PathVariable("id") Integer id) {


        return payFeignApi.deletePay(id);
    }

    @PutMapping("/consumer/update")
    @Operation(summary = "更新订单", description = "用户更新订单信息")
    public ResultData updatePay(@RequestBody Pay pay) {

        return payFeignApi.updatePay(pay);
    }

    @GetMapping("/consumer/pay/get/info")
    @Operation(summary = "获得info", description = "获得consul键值对信息测试负载平衡")
    private ResultData getInfoByConsul() {

        return payFeignApi.getInfo();
    }

    @Resource
    private DiscoveryClient discoveryClient;
    @GetMapping("/consumer/discovery")
    @Operation(summary = "获得当前第一个service信息", description = "测试consul获得所有的service Instance 信息")
    public ResultData<String> discovery()
    {
        List<String> services = discoveryClient.getServices();
        for (String element : services) {
            System.out.println(element);
        }

        System.out.println("===================================");

        List<ServiceInstance> instances = discoveryClient.getInstances("cloud-payment-service");
        for (ServiceInstance element : instances) {
            System.out.println(element.getServiceId()+"\t"+element.getHost()+"\t"+element.getPort()+"\t"+element.getUri());
        }

        return ResultData.success(instances.get(0).getServiceId()+":"+instances.get(0).getPort());
    }
}
