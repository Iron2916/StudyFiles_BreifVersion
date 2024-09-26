package Demo2线程同步_线程安全;


/**
 * 静态代码块：锁：只想统一体类
 */

class RunnableBlockCode implements Runnable {

    static private int ticket = 10000;
    @Override
    public void run() {

        while (true) {

            synchronized (RunnableBlockCode.class) {   //RunnableBlockCode.class

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
            }
        }
    }
}

public class SynchronizedByBlockCode2 {

    public static void main(String[] args) {

        final RunnableBlockCode run = new RunnableBlockCode();
        new Thread(run, "线程一").start();
        new Thread(run, "线程二").start();
        new Thread(run, "线程三").start();
        new Thread(run, "线程四").start();
    }
}
