package com.iron.Demo03_关闭线程池的两种方法;

import lombok.Getter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class P1_ShutDown
{


    public static void main(String[] args) {

        // 创建一个单线程执行器
        final ExecutorService threadPool = Executors.newSingleThreadExecutor();

        // 循环执行
        for (int i=0; i<10; i++) {

            System.out.println("第："+i+" 次提交");

            try
            {
                threadPool.execute(new Task(i));
            } catch (Exception e) {

                // 执行失败打印
                e.printStackTrace();
                System.out.println("第" + i + "次执行失败执行中断了");
            }


            //i等于5的时候shutdown，意味着从第6次开始就不能提交新任务
            if (i == 5)
            {
                threadPool.shutdown();
            }

        }

        System.out.println("main方法执行完成了！----- 不会等待线程执行完毕");
    }
    static class Task implements Runnable {

        @Getter
        private String name;

        public Task(int i) {
            name = "task-" + i;
        }


        @Override
        public void run() {

            try {
                TimeUnit.SECONDS.sleep(2);
                System.out.println("sleep completed, " + getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("interrupted：" + getName());
            }

            // 执行成功打印
            System.out.println(getName() + " finished");
            System.out.println();
        }
    }
}
