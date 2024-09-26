package Demo7Jdk5新增的两种启动线程方式;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

class MyCallable implements Callable {


    @Override
    public Object call() throws Exception {
        // 返回0-100的偶数
        int sum = 0;

        for (int i=0; i<=100; i++) {

            if (i % 2 == 0) {

                sum += i;
            }
        }

        return sum;
    }
}
public class TestCallable{

    public static void main(String[] args) {

        final MyCallable myCallable = new MyCallable();
        final FutureTask futureTask = new FutureTask<Integer>(myCallable);
        new Thread(futureTask).start();

        System.out.println("main线程开始执行");
        try {

            System.out.println(futureTask.isDone());
            System.out.println("sum：" + futureTask.get());       //这里获得对象时候，主线程会被阻塞，等待子线程执行完成获得结果
            System.out.println(futureTask.isDone());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }

        System.out.println("main线程执行结束");
    }
}
