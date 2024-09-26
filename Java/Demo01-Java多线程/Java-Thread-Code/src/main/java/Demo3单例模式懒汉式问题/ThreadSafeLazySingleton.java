package Demo3单例模式懒汉式问题;

/**
 * 双检加锁解懒汉式的线程安全问题
 */
public class ThreadSafeLazySingleton {

    // volatile保证指令有序，高并发可能创建对象时候指定重拍从而导致空指针(JVM自动进行的优化,比如是应该先执行A再执行B，优化之后可能限制性B再执行A)
    public static volatile ThreadSafeLazySingleton instance;

    public static ThreadSafeLazySingleton getInstance() {

        // 为什么双检：就是为了第一初始化防止多线程进入获得不同值，所以得加入第二个检查。
        if (instance == null) {
            // 高并发进入两个线程：先后进入两个线程一二

            synchronized(ThreadSafeLazySingleton.class) {
                // 一线程进为null，二线程被赋值(一线程执行完后)，所以得第二层判断
                if (instance == null) {

                    try {
                        Thread.sleep(100);          // 让多个线程进来
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    instance = new ThreadSafeLazySingleton();
                }
            }
        }


        return instance;
    }

    static ThreadSafeLazySingleton instance1;
    static ThreadSafeLazySingleton instance2;

    public static void main(String[] args) throws InterruptedException {

        final Thread t1 = new Thread() {

            @Override
            public void run() {

                instance1 = ThreadSafeLazySingleton.getInstance();
            }
        };

        final Thread t2 = new Thread() {

            @Override
            public void run() {

                instance2 = ThreadSafeLazySingleton.getInstance();
            }
        };

        t1.start();
        t2.start();

        // 首先执行玩t1,t2
        t1.join();
        t2.join();

        // 检测最终的结果
        System.out.println(instance1 == instance2);
    }
}
