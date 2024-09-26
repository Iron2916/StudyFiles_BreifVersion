package com.iron.Demo02_ThreaPool线程池;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class P1_FixedThreadPool {

    public static void main(String[] args) {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 10; i++) {
            final int index = i;
            fixedThreadPool.execute(() -> {
                System.out.println("Task: " + index + " executed by " + Thread.currentThread().getName());
            });
        }
        fixedThreadPool.shutdown();
    }
}
