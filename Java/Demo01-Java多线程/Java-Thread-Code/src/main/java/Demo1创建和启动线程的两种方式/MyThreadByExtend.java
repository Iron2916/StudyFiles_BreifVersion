package Demo1创建和启动线程的两种方式;

public class MyThreadByExtend extends Thread{

    // 构造器设置线程名称
    public MyThreadByExtend(String name) {
        super(name);
    }

    // 重写run方法
    @Override
    public void run() {

        for (int i=0; i<10; i++) {

            System.out.println("线程：" + this.getName() + "执行----" + i);
        }
    }

    public static void main(String[] args) throws InterruptedException {

        // 创建线程一：
        final MyThreadByExtend thread1 = new MyThreadByExtend("子线程一");
        thread1.setPriority(1); //设置优先级最低
        thread1.start();
        //thread1.join();       //等待该线程完成

        // 创建线程二：
        final MyThreadByExtend thread2 = new MyThreadByExtend("子线程二");
        thread2.setPriority(10); //设置优先级最高
        thread2.start();

        for (int i=0; i<10; i++) {

            System.out.println("线程：Main" + "执行----" + i);
        }
    }
}
