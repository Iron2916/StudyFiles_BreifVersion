import java.util.stream.Stream;

public class 创建方式三_StreamOf
{
    public static void main(String[] args) {
        Stream<Integer> integerStream = Stream.of(1, 2, 3);
        integerStream.map(var -> var * 2).forEach(System.out::println);
    }
}
