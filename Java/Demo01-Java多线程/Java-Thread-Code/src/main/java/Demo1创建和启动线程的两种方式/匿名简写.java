package Demo1创建和启动线程的两种方式;

public class 匿名简写 {

    public static void main(String[] args) {

        // 继承方式的匿名简写
        new Thread("线程一") {

            @Override
            public void run() {

                for (int i=0; i<10; i++) {

                    System.out.println("线程：" + this.getName() + "执行----" + i);
                }
            }
        }.start();

        // 实现方式匿名简写
        new Thread(new Runnable() {
            @Override
            public void run() {

                for (int i=0; i<10; i++) {

                    System.out.println("线程：" + Thread.currentThread().getName() + "执行----" + i);
                }
            }
        }, "线程二").start();

    }
}
