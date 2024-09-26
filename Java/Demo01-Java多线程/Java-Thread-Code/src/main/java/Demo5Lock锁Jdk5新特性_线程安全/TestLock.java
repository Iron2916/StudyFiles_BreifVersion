package Demo5Lock锁Jdk5新特性_线程安全;

import java.util.concurrent.locks.ReentrantLock;

class MyRunnable implements Runnable {

    static int ticket = 10;

    //1. 创建Lock的实例，必须确保多个线程共享同一个Lock实例
    private final ReentrantLock lock = new ReentrantLock();

    @Override
    public void run() {

        while (true) {

            lock.lock();

            try {
                if (ticket > 0) {

                    try {
                        Thread.sleep(1);           //增加并发多线程挤入的概率
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    System.out.println(Thread.currentThread().getName() + "卖出一张票，票号:" + ticket);
                    ticket--;
                } else {
                    System.out.println("当前票已经售卖完毕!");
                    break;
                }
            } finally {
                lock.unlock();
            }

        }
    }
}
public class TestLock {

    public static void main(String[] args) {

        final MyRunnable run = new MyRunnable();

        new Thread(run, "线程一").start();
        new Thread(run, "线程二").start();
        new Thread(run, "线程三").start();
    }
}

