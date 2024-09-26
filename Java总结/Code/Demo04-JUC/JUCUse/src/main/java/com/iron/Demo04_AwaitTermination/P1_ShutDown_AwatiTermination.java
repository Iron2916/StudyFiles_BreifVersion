package com.iron.Demo04_AwaitTermination;

import lombok.Getter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;

public class P1_ShutDown_AwatiTermination {

    public static void main(String[] args) {

        ExecutorService threadPool = Executors.newSingleThreadExecutor();
        //提交10个任务，在第5个任务提交完，准备提交第6个的时候执行shutdown
        for (int i = 1; i <=10; i++)
        {
            System.out.println("第："+i+" 次提交");
            try {
                threadPool.execute(new Task(i));
            } catch (RejectedExecutionException e) {
                System.out.println("rejected, task-" + i);
            }
            //i等于5的时候shutdown，意味着从第6次开始就不能提交新任务
            if (i == 5)
            {
                threadPool.shutdown();
                System.out.println("i等于5的时候shutdown，意味着从第6次开始就不能提交新任务");
                System.out.println();
            }
        }

        try
        {

            // 等100秒，100没有执行完成就不阻塞
            boolean isStop = threadPool.awaitTermination(100, TimeUnit.SECONDS);
            System.out.println("is pool isStop: " + isStop);
            System.out.println(Thread.currentThread().getName()+"\t"+"所有线程执行完毕");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * runnable 任务类
     */
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
