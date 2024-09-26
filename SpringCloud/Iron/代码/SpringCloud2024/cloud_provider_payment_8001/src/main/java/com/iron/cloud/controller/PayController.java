package com.iron.cloud.controller;

import com.iron.cloud.pojo.Pay;
import com.iron.cloud.response.ResultData;
import com.iron.cloud.service.PayService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@Tag(name = "支付微服务模块", description = "支付CRUD")
public class PayController {

    @Autowired
    PayService payService;

    @PostMapping(value = "/pay/add")
    @Operation(summary = "新增", description = "新增支付流水方法，json做参数")
    public ResultData addPay(@RequestBody Pay pay){

        System.out.println("hello world");
        System.out.println("hello world");
        System.out.println(pay + "hello world");
        int i = payService.add(pay);
        return ResultData.success("成功插入记录，返回值："+i);
    }
    @DeleteMapping(value = "/pay/del/{id}")
    @Operation(summary = "删除", description = "删除流水")
    public ResultData deletePay(@PathVariable("id") Integer id) {

        return ResultData.success("成功删除：" + payService.delete(id));
    }
    @PutMapping(value = "/pay/update")
    @Operation(summary = "更新", description = "更新流水")
    public ResultData updatePay(@RequestBody Pay pay){
//        Pay pay = new Pay();
//        BeanUtils.copyProperties(payDTO, pay);

        int i = payService.update(pay);
        return ResultData.success("成功修改记录，返回值："+i);
    }

    @GetMapping(value = "/pay/get/{id}")
    @Operation(summary = "获得单个流水", description = "获得单个流水")
    public ResultData<Pay> getById(@PathVariable("id") Integer id){

        try {
            // 测试 OpenFeign 默认超时时间
            TimeUnit.SECONDS.sleep(62);
        } catch (InterruptedException e) {

            throw new RuntimeException(e);
        }

        return ResultData.success(payService.getById(id));
    }//全部查询getall作为家庭作业

    @GetMapping(value = "/pay/getAll")
    @Operation(summary = "获得所有流水", description = "获取所有流水")
    public ResultData<List<Pay>> getAll() {

        return ResultData.success(payService.getAll());
    }

    @Value("${server.port}")
    String port;
    @GetMapping(value = "/pay/get/info")
    @Operation(summary = "获得consul里面信息", description = "测试获得consul里面信息")
    public ResultData getInfo(@Value("${iron.info}") String info) {

        return ResultData.success("端口号：" + port + " info：" + info);
    }
}
