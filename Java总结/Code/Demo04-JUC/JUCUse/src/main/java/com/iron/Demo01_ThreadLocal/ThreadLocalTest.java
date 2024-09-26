package com.iron.Demo01_ThreadLocal;

import lombok.Getter;

import java.util.concurrent.CountDownLatch;

public class ThreadLocalTest {
    // 资源类，操作得加锁
    static class Su7 {

        @Getter
        private int saleTotal;//本门店总体销售额
        public synchronized void saleTotal()
        {
            saleTotal++;
        }
        // 初始化ThreadLocal
        ThreadLocal<Integer> salePersonal = ThreadLocal.withInitial(() -> 0);

        public void salePersonal()
        {
            salePersonal.set(1+salePersonal.get());
        }

    }

    public static void main(String[] args) throws InterruptedException {

        //线程操作资源类
        final Su7 su7 = new Su7();

        // 同步辅助类，它允许一个或多个线程等待，直到在其他线程中执行的一组操作完成。
        CountDownLatch countDownLatch = new CountDownLatch(3);

        for (int i = 1; i <=3; i++) {
            new Thread(() -> {
                try {
                    for (int j = 1; j <= 3000; j++) {
                        su7.saleTotal();//本门店需要统计的总数
                        su7.salePersonal();//本门店各自销售自己的，独立的销售额
                    }
                    System.out.println(Thread.currentThread().getName()+"\t"+"号销售卖出："+su7.salePersonal.get());
                } finally {
                    countDownLatch.countDown();
                    su7.salePersonal.remove();  // 防止 TreahdLocal 线程复用，所以执行完成后必须remove
                }
            },String.valueOf(i)).start();
        }

        // 执行完后一下放出
        countDownLatch.await();

        System.out.println(Thread.currentThread().getName()+"\t"+"销售总额："+su7.getSaleTotal());
    }
}
