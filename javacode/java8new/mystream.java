package java8new;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.Collectors;
public class mystream {
    public static void main(String[] args) {
        //1.通过 stream() 或 parallelStream() 方法
        List<String> list = new ArrayList<>();
        Stream<String> s1 = list.stream();
        Stream<String> s2 = list.parallelStream();

        //2.通过 Arrays 中的静态方法
        int[] i = new int[10];
        IntStream s3 = Arrays.stream(i);

        //3.通过 Stream 类中的 of() 方法
        Stream.of("11","22","33");

        //4.创建无限流 - 迭代
        Stream<Integer> s4 = Stream.iterate(0, (x) -> x + 2);

        //生成
        Stream.generate(() -> Math.random())
            .filter(Double -> Double < 0.2)
            .limit(5)
            .forEach(System.out :: println);

        List<String>strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
        List<String> filtered = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());

        System.out.println("筛选列表: " + filtered);
        String mergedString = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.joining(", "));
        System.out.println("合并字符串: " + mergedString);
    }
}
