package com.iron.Demo05_优雅关闭线程池;

import lombok.Getter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;

public class TurnOffThreadPool {
    public static void main(String[] args) {

        ExecutorService threadPool = Executors.newSingleThreadExecutor();
        //提交10个任务，在第5个任务提交完，准备提交第6个的时候执行shutdown
        for (int i = 1; i <=10; i++)
        {
            System.out.println("第："+i+" 次提交");
            try
            {
                threadPool.execute(new Task(i));
            } catch (RejectedExecutionException e) {

                System.out.println("rejected, task-" + i);  // i=6开始因为线程池关闭导致执行失败
            }
            //i等于5的时候shutdown，意味着从第6次开始就不能提交新任务
            if (i == 5)
            {
                // 直接使用官网线程池关闭示例代码
                shutdownAndAwaitTermination(threadPool);
                System.out.println("i等于5的时候shutdown，意味着从第6次开始就不能提交新任务");
                System.out.println();
            }
        }

        System.out.println("主线程执行完毕");
    }

    /**
     * 官网关闭线程池的代码
     * @param pool
     */
    static void shutdownAndAwaitTermination(ExecutorService pool) {
        pool.shutdown(); // Disable new tasks from being submitted
        try {
            // Wait a while for existing tasks to terminate
            if (!pool.awaitTermination(60, TimeUnit.SECONDS)) {
                pool.shutdownNow(); // Cancel currently executing tasks
                // Wait a while for tasks to respond to being cancelled
                if (!pool.awaitTermination(60, TimeUnit.SECONDS))
                    System.err.println("Pool did not terminate");
            }
        } catch (InterruptedException ex) {
            // (Re-)Cancel if current thread also interrupted
            pool.shutdownNow();
            // Preserve interrupt status
            Thread.currentThread().interrupt();
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
