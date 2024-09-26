package Demo2线程同步_线程安全;

/**
 * 静态代码块：锁：创建一个静态唯一的 ”看门狗“
 */
class ThreadByBlockCode extends Thread {

    static int ticket = 10000;

    static private Object dog = new Object();   //这里创建一个唯一的静态对象看门狗用于线程同步的 锁
    public ThreadByBlockCode(String name) {
        super(name);
    }

    @Override
    public void run() {

        while (true) {

            synchronized (ThreadByBlockCode.class) {   // 这里的同步锁是自己设置的唯一看门狗 或 指向统一的类

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

public class SynchronizedByBlockCode1 {

    public static void main(String[] args) {

        final ThreadByBlockCode t1 = new ThreadByBlockCode("线程一");
        final ThreadByBlockCode t2 = new ThreadByBlockCode("线程二");
        final ThreadByBlockCode t3 = new ThreadByBlockCode("线程三");
        t1.start();
        t2.start();
        t3.start();
    }
}
