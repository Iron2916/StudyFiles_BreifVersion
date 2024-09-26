package Demo2线程同步_线程安全;

class QuestionThread extends Thread {

    static int stock = 100;
    public QuestionThread(String name) {

        super(name);
    }
    @Override
    public void run() {

        while (stock > 0) {

            try {
                Thread.sleep(10);//加入这个，使得问题暴露的更明显
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "购入：" + stock);
            stock--;
        }
    }
}
public class QuestionExtend {

    public static void main(String[] args) {

        final QuestionThread costumer1 = new QuestionThread("用户一");
        costumer1.start();

        final QuestionThread costumer2 = new QuestionThread("用户二");
        costumer2.start();
    }
}
