package Demo6生产者消费者问题_线程通信;


/**
 * 生产者
 */
class Producer extends Thread{

    Clerk clerk;

    public Producer(String name, Clerk clerk) {
        super(name);
        this.clerk = clerk;
    }

    @Override
    public void run() {

        while (true) {

            clerk.AddProduct();
        }
    }
}

/**
 * 生产者
 */
class Consumer extends Thread{

    Clerk clerk;

    public Consumer(String name, Clerk clerk) {
        super(name);
        this.clerk = clerk;
    }

    @Override
    public void run() {

        while (true) {

            clerk.MinusProduct();
        }
    }
}

/**
 * 资源类，实现了商品的增加，删除方法
 */
class Clerk {

    private int productNum = 0;
    public int maxNum = 20;
    public int minNum = 1;

    public synchronized void AddProduct() {

        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        if (productNum < maxNum) {

            productNum++;
            System.out.println(Thread.currentThread().getName() +
                    "生产了第" + productNum + "个产品");
            this.notifyAll();       // 唤醒消费者进行消费(满足消费条件进行消费，因为现在至少有一个商品)
        } else {

            System.out.println("产品充足不需要再生产");
            try {
                this.wait();        // 产品充足进行等待(阻塞当前线程等待下一次的唤醒)
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }


    public synchronized  void MinusProduct() {

        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        if (productNum > minNum) {

            System.out.println(Thread.currentThread().getName() +
                    "消费了第" + productNum + "个产品");
            productNum--;

            this.notifyAll();   // 唤醒生产者进行生产(满足生产条件进行生产，因为消费了一个满足小于最大库存)
        } else {

            try {
                this.wait();    // 产品不足进行等待(阻塞当前线程等待下一次的唤醒)
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("产品已售罄！");
        }
    }
}
public class ProducerConsumer {

    public static void main(String[] args) {

        final Clerk clerk = new Clerk();

        new Producer("生产者一", clerk).start();
        new Producer("生产者二", clerk).start();

        new Consumer("消费者一", clerk).start();
        new Consumer("消费者二", clerk).start();
    }
}
