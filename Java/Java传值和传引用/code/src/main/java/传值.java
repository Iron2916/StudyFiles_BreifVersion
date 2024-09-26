/**
 * 基本数据类型都是传值
 */
public class 传值 {
    // 基本数据类型

    public static void main(String[] args) {
        int s = 1;
        System.out.println("args = [" + s + "]");
        change(s);
        System.out.println("args = [" + s + "]");
    }

    private static void change(int i){
        i = i* 5;
    }

}
