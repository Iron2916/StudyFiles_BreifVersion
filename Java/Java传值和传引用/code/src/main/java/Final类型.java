/**
 * String、Integer、Double等immutable类型因为类的变量设为final属性，无法被修改，只能重新赋值或生成对象。(相当赋值的时候断开了)
 */
public class Final类型 {
    public static void main(String[] args) {
        String s = new String("abcd");
        System.out.println("args = [" + s + "]");
        change(s);
        System.out.println("args = [" + s + "]");
    }

    private static void change(String i){
        i = i + " test value";
    }
}
