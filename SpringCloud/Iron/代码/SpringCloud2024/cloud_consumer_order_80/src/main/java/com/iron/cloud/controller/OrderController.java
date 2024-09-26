package com.iron.cloud.controller;

import com.iron.cloud.pojo.Pay;
import com.iron.cloud.pojo.PayDTO;
import com.iron.cloud.response.ResultData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Tag(name="用户订单模块", description = "用户操作模块")
@RestController
public class OrderController {

    // public static final String PaymentSrv_URL = "http://localhost:8001";//先写死，硬编码
    public static final String PaymentSrv_URL = "http://cloud-payment-service";//服务注册中心上的微服务名称
    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/consumer/add")
    @Operation(summary = "支付订单", description = "订单进行支付")
    public ResultData addOrder(@RequestBody Pay pay){

        System.out.println(pay);
        return restTemplate.postForObject(PaymentSrv_URL + "/pay/add",pay,ResultData.class);
    }

    @GetMapping("/consumer/get/{id}")
    @Operation(summary = "得到订单信息", description = "用户获得订单信息")
    public ResultData getPayInfo(@PathVariable("id") Integer id){

        return restTemplate.getForObject(PaymentSrv_URL + "/pay/get/"+id, ResultData.class, id);
    }

    @DeleteMapping("/consumer/del/{id}")
    @Operation(summary = "删除订单", description = "用户删除订单信息")
    public ResultData deletePay(@PathVariable("id") Integer id) {

        restTemplate.delete(PaymentSrv_URL + "/pay/del/" + id);
        return ResultData.success("删除成功");
    }

    @PutMapping("/consumer/update")
    @Operation(summary = "更新订单", description = "用户更新订单信息")
    public ResultData updatePay(@RequestBody Pay pay) {

        restTemplate.put(PaymentSrv_URL + "/pay/update", pay);
        return ResultData.success("更新成功！");
    }

    @GetMapping("/consumer/pay/get/info")
    @Operation(summary = "获得info", description = "获得consul键值对信息测试负载平衡")
    private ResultData getInfoByConsul() {

        return restTemplate.getForObject(PaymentSrv_URL + "/pay/get/info", ResultData.class);
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
