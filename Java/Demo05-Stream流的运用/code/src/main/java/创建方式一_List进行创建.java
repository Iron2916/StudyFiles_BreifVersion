import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class 创建方式一_List进行创建
{
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1,2, 3, 4);
        Stream<Integer> stream = list.stream();
        List<Integer> collect = stream.map(var -> var * 2).collect(Collectors.toList());
        System.out.println(collect);
    }
}
