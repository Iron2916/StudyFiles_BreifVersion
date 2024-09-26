package com.iron.Demo07_多线程百万数据处理.service.Impl;

import com.iron.Demo07_多线程百万数据处理.Utils.TaskBatchSendUtils;
import com.iron.Demo07_多线程百万数据处理.service.CouponService;
import jakarta.annotation.Resource;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

@Service
public class CouponServiceImpl implements CouponService {

    @Resource
    ThreadPoolTaskExecutor threadPoolTaskExecutor;

    private final int COUPON_NUM = 10000000;    // 需要发送的优惠券数量

    @Override
    public void batchCoupon()
    {
        final long preTime = System.currentTimeMillis();    // 执行之前的时间

        try
        {
            final CountDownLatch countDownLatch = new CountDownLatch(COUPON_NUM);
            for (int i=0; i<COUPON_NUM; i++)
            {
                int index = i;
                threadPoolTaskExecutor.execute(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        try
                        {
                            System.out.println(Thread.currentThread().getName() + "派发优惠券-------------" + index);
                        } finally {
                            countDownLatch.countDown();
                        }
                    }
                });
            }
            countDownLatch.await(); // 阻塞当前线程，直到所有的线程任务完成，一次性执行
        } catch (Exception e) {

            e.printStackTrace();
        }

        final long afterTime = System.currentTimeMillis();  // 执行完成后的时间
        System.out.println("任务执行完毕，执行耗时：" + (afterTime - preTime) + "毫秒");
    }

    @Override
    public void batchCouponByModel()
    {
        final long preTime = System.currentTimeMillis();
        List<String> tasks = new ArrayList<>();


        for (int i=0; i<COUPON_NUM; i++)
        {
            tasks.add("优惠券---------------" + i);
        }
        try
        {
            TaskBatchSendUtils.send(tasks, threadPoolTaskExecutor, TaskBatchSendUtils::disposeTask);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        final long afterTime = System.currentTimeMillis();
        System.out.println("执行完成，执行时间为：" + (afterTime - preTime));
    }


}
