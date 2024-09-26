import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class 创建方式二_数组进行创造
{
    public static void main(String[] args) {
        int []arr = new int[]{1, 2, 3};
        IntStream stream = Arrays.stream(arr);
        IntStream intStream = stream.map(var -> var * 2);
        intStream.forEach(System.out::println);
    }
}
