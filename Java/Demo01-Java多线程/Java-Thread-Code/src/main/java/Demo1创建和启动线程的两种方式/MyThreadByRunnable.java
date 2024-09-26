package Demo1创建和启动线程的两种方式;

// 实现runnable方法
class MyRunnable implements Runnable {


    @Override
    public void run() {

        for (int i=0; i<10; i++) {

            System.out.println("线程：" + Thread.currentThread().getName() + "执行----" + i);
        }
    }

}

public class MyThreadByRunnable {

    public static void main(String[] args) {

        final MyRunnable run = new MyRunnable();

        // 线程一
        final Thread t1 = new Thread(run, "线程一");
        t1.start();

        // 线程二
        final Thread t2 = new Thread(run, "线程二");
        t2.start();

    }
}

