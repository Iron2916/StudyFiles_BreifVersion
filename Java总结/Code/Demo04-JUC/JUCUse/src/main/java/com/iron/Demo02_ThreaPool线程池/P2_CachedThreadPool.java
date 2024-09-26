package com.iron.Demo02_ThreaPool线程池;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class P2_CachedThreadPool {

    public static void main(String[] args) {
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 100; i++) {
            final int index = i;
            cachedThreadPool.execute(() -> {
                System.out.println("Task: " + index + " executed by " + Thread.currentThread().getName());
            });
        }
        cachedThreadPool.shutdown();
    }
}
