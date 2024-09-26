import java.util.stream.Stream;

public class 创建方式四_创建无限流
{
    public static void main(String[] args)
    {
        // 创建方式一：public static<T> Stream<T> iterate(final T seed, final UnaryOperator<T> f)
        Stream.iterate(0, x -> x + 2).forEach(System.out::println);

        // 创建方式二：public static<T> Stream<T> generate(Supplier<T> s)
        Stream.generate(Math::random).forEach(System.out::println);
    }
}
