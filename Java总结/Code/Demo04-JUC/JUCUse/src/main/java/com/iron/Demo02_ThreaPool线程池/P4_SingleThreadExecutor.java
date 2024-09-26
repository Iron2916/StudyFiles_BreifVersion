package com.iron.Demo02_ThreaPool线程池;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class P4_SingleThreadExecutor {

    public static void main(String[] args) {
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 10; i++) {
            final int index = i;
            singleThreadExecutor.execute(() -> {
                System.out.println("Task: " + index + " executed by " + Thread.currentThread().getName());
            });
        }
        singleThreadExecutor.shutdown();
    }
}
