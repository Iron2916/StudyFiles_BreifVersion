package com.iron.Demo07_多线程百万数据处理.Utils;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.function.Consumer;

public class TaskBatchSendUtils
{
    public static <T> void send(List<T> taskList, Executor threadPool, Consumer<? super T> consumer) throws InterruptedException
    {
        if (taskList == null || taskList.size() == 0)
        {
            return;
        }

        if(Objects.isNull(consumer))
        {
            return;
        }

        CountDownLatch countDownLatch = new CountDownLatch(taskList.size());

        for (T couponOrShortMsg : taskList)
        {
            threadPool.execute(() ->
            {
                try
                {
                    consumer.accept(couponOrShortMsg);
                } finally {
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();
    }

     // 对应的业务静态类类，通过静态类因公传递到上面的send的 Consumer<? super T> consumer 方法中
    public static void disposeTask(String task)
    {
        System.out.println(String.format(Thread.currentThread().getName() + "【%s】disposeTask下发优惠卷或短信成功", task));
    }

    public static void disposeTaskV2(String task)
    {
        System.out.println(String.format(Thread.currentThread().getName() + "【%s】disposeTask下发邮件成功", task));
    }

}