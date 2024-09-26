package com.iron.cloud.apis;

import com.iron.cloud.pojo.Pay;
import com.iron.cloud.response.ResultData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "cloud-payment-service")
public interface PayFeignApi {

    @PostMapping(value = "/pay/add")
    public ResultData addPay(@RequestBody Pay pay);
    @DeleteMapping(value = "/pay/del/{id}")
    public ResultData deletePay(@PathVariable("id") Integer id);
    @PutMapping(value = "/pay/update")
    public ResultData updatePay(@RequestBody Pay pay);

    @GetMapping(value = "/pay/get/{id}")
    public ResultData<Pay> getById(@PathVariable("id") Integer id);

    @GetMapping(value = "/pay/getAll")
    public ResultData<List<Pay>> getAll();

    @GetMapping(value = "/pay/get/info")
    public ResultData getInfo();

    // 测试 CircuiBreaker(熔断)
    @GetMapping(value = "/pay/circuit/{id}")
    public String myCircuit(@PathVariable("id") Integer id);

    @GetMapping(value = "/pay/bulkhead/{id}")
    public String myBulkhead(@PathVariable("id") Integer id);

    @GetMapping(value = "/pay/ratelimit/{id}")
    public String myRatelimit(@PathVariable("id") Integer id);

    @GetMapping(value = "/pay/micrometer/{id}")
    public String myMicrometer(@PathVariable("id") Integer id);
}
