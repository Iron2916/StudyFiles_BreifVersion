import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class 主流操作方式
{
    // 过滤 filter
    @Test
    public void test01(){
        //1、创建Stream
        Stream<Integer> stream = Stream.of(1,2,3,4,5,6);

        stream = stream.filter(t -> t%2==0);

        //3、终结操作：例如：遍历
        stream.forEach(System.out::println);
    }

    //  foreEach 循环
    @Test
    public void test02(){
        Stream.of(1,2,3,4,5,6)
                .filter(t -> t%2==0)
                .forEach(System.out::println);
    }

    //  distinck去重
    @Test
    public void test03(){
        Stream.of(1,2,3,4,5,6,2,2,3,3,4,4,5)
                .distinct()
                .forEach(System.out::println);
    }

    // limit 获取前面num个
    @Test
    public void test04(){
        Stream.of(1,2,3,4,5,6,2,2,3,3,4,4,5)
                .limit(3)
                .forEach(System.out::println);
    }

    // skip 跳过前num个，返回之后的元素
    @Test
    public void test06(){
        Stream.of(1,2,3,4,5,6,2,2,3,3,4,4,5)
                .skip(5)
                .forEach(System.out::println);
    }

    // peek：中间调试操作
    @Test
    public void test08(){
        Stream.of(10, 11, 12, 13)
                .filter(n -> n % 2 == 0)
                .peek(e -> System.out.println("Debug filtered value: " + e))
                .map(n -> n * 10)
                .peek(e -> System.out.println("Debug mapped value: " + e))
                .collect(Collectors.toList());

        // 注意：在java9之后count这种短路操作会导致peek失效
        long cnt = Stream.of(10, 11, 12, 13)
                .peek(e -> System.out.println("Debug: " + e))
                .count();
        System.out.println("总共个数: " + cnt);
    }

    // 多线程的并行操作
    @Test
    public void test() {
        Stream.of(15, 10, 17, 11)
                .parallel()
                .peek(x -> System.out.println("当前线程为:" + Thread.currentThread().getName()))
                .forEach(System.out::println);
    }

    // sorted：排序
    @Test
    public void test09(){
        //希望能够找出前三个最大值，前三名最大的，不重复
        Stream.of(11,2,39,4,54,6,2,22,3,3,4,54,54)
                .distinct()
                .sorted((t1,t2) -> -Integer.compare(t1, t2))//Comparator接口  int compare(T t1, T t2)
                .limit(3)
                .forEach(System.out::println);
    }

    // map：操作每个元素
    @Test
    public void test10(){
        Stream.of(1,2,3,4,5)
                .map(t -> t+=1)//Function<T,R>接口抽象方法 R apply(T t)
                .forEach(System.out::println);
    }

    //  flatMap 打散操作每个元素
    @Test
    public void test12(){
        String[] arr = {"hello","world","java"};
        Arrays.stream(arr)
                .flatMap(t -> Stream.of(t.split("|")))//Function<T,R>接口抽象方法 R apply(T t)  现在的R是一个Stream
                .forEach(System.out::println);
    }

    // reduce：归约
    @Test
    public void test11() {
        Stream.of(1, 2, 3, 4, 5, 6)
                .reduce((a, b) ->{
                    System.out.println( a + " + "  + b + " = " + (a+b));
                    return a+b;
                });
    }

    // collect：收集，流式转换。
    @Test
    public void test13() {
        Optional<Integer> max = Stream.of(1, 2, 3, 4, 5, 6)
                .max((t1,t2) -> -Integer.compare(t1, t2));

        List<Integer> list = Arrays.asList(1, 1, 1, 2, 3, 4, 5);
        List<Integer> list1= list.stream().collect(Collectors.toList());

        Set<Integer> set= list.stream().collect(Collectors.toSet());
        Collection<Integer> emps =list.stream().collect(Collectors.toCollection(ArrayList::new));

        long count = list.stream().collect(Collectors.counting());
        Double average = list.stream().collect(Collectors.averagingInt(e -> e));

        IntSummaryStatistics summaryStatistics = list.stream().collect(Collectors.summarizingInt(e -> e));
        System.out.println(summaryStatistics.getMax());

        Map<Integer, List<Integer>> map= list.stream().collect(Collectors.groupingBy(e -> e));
        System.out.println(map);

        Map<Boolean, List<Integer>> collect1 = list.stream().collect(Collectors.partitioningBy(e -> e == 2));
        System.out.println(collect1);

        Optional<Integer> collect = list.stream().collect(Collectors.reducing((a, b) -> a + b));
        System.out.println(collect);

        Integer size = list.stream().collect(Collectors.collectingAndThen(Collectors.toList(), List::size));
        System.out.println("size = " + size);
    }
}
