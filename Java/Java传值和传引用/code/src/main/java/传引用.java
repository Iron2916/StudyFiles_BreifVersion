/**
 * 对象都是传引用的
 */
public class 传引用 {

    public static class Score{
        private int value;

        public int getValue()  {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }

    public static void main(String[] args) {
        Score score = new Score();
        score.setValue(1);
        System.out.println("args = [" + score.getValue() + "]");
        change(score);
        System.out.println("after args = [" + score.getValue() + "]");
    }

    private static void change(Score score){
        score.setValue(2);

        // 如果此时被设置为null，就相当于断开了。
//        score = null;
//        score.setValue(10);
    }
}
