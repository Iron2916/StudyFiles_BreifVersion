package com.iron.Demo01_ThreadLocal;

import lombok.Getter;

public class ThreadLoclTestLock {

    // 资源类，操作得加锁
    static class Su7 {

        @Getter
        private int totalNum = 0;

        // 加锁的写法
        public synchronized void saleOneSu7() {

            this.totalNum += 1;
        }

    }

    public static void main(String[] args) throws InterruptedException {

        // 创建一个资源类
        final Su7 su7 = new Su7();

        // 模拟三个销售员,每人买三辆
        for (int i=1; i<=3; i++) {

            new Thread(new Runnable() {
                @Override
                public void run() {

                    for (int j=0; j<30000; j++) {

                        su7.saleOneSu7();
                    }
                }
            }).start();
        }

        // 停止3秒等待宗宪成成
        Thread.sleep(3000);

        // 总销售量
        System.out.println("总销售量：" + su7.getTotalNum());
    }
}
