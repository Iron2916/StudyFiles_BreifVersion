package com.iron.Demo02_ThreaPool线程池;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class P3_ScheduledThreadPool {

    public static void main(String[] args) {
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);

        for (int i=0; i<3; i++) {

            int index = i;
            scheduledThreadPool.schedule(() -> {
                System.out.println("Task: " + index + " executed by " + Thread.currentThread().getName());
            }, 3, TimeUnit.SECONDS);
        }
        scheduledThreadPool.shutdown();
    }
}
