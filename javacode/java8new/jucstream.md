## 集合数组的传输

Java 8 API添加了一个新的抽象称为流Stream，可以让你以一种声明的方式处理数据。

Stream 使用一种类似用 SQL 语句从数据库查询数据的直观方式来提供一种对 Java 集合运算和表达的高阶抽象。

Stream API可以极大提高Java程序员的生产力，让程序员写出高效率、干净、简洁的代码。

这种风格将要处理的元素集合看作一种流， 流在管道中传输， 并且可以在管道的节点上进行处理， 比如筛选， 排序，聚合等。

元素流在管道中经过中间操作（intermediate operation）的处理，最后由最终操作(terminal operation)得到前面处理的结果。

```
+--------------------+       +------+   +------+   +---+   +-------+
| stream of elements +-----> |filter+-> |sorted+-> |map+-> |collect|
+--------------------+       +------+   +------+   +---+   +-------+
```

```java
List<Integer> transactionsIds = 
widgets.stream()
             .filter(b -> b.getColor() == RED)
             .sorted((x,y) -> x.getWeight() - y.getWeight())
             .mapToInt(Widget::getWeight)
             .sum();
```
## 特点
Stream（流）是一个来自数据源的元素队列并支持聚合操作

元素是特定类型的对象，形成一个队列。 Java中的Stream并不会存储元素，而是按需计算。
数据源 流的来源。 可以是集合，数组，I/O channel， 产生器generator 等。
聚合操作 类似SQL语句一样的操作， 比如filter, map, reduce, find, match, sorted等。
和以前的Collection操作不同， Stream操作还有两个基础的特征：

Pipelining: 中间操作都会返回流对象本身。 这样多个操作可以串联成一个管道， 如同流式风格（fluent style）。 
这样做可以对操作进行优化， 比如延迟执行(laziness)和短路( short-circuiting)。

内部迭代： 以前对集合遍历都是通过Iterator或者For-Each的方式, 
显式的在集合外部进行迭代， 这叫做外部迭代。 
Stream提供了内部迭代的方式， 
通过访问者模式(Visitor)实现。

## 生成stream
```java
List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
List<String> filtered = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());
```

```java
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
            .limit(5)
            .forEach(System.out :: println);
```

## 中间操作

### 筛选切片
```java
List<String>strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
// 获取空字符串的数量
long count = strings.stream().filter(string -> string.isEmpty()).count();
```
### 获取指定数量的流

```java
Random random = new Random();
random.ints().limit(10).forEach(System.out::println);
```

### sorted
```java
Random random = new Random();
random.ints().limit(10).sorted().forEach(System.out::println);
```

### 映射
```java
List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
// 获取对应的平方数
List<Integer> squaresList = numbers.stream().map( i -> i*i).distinct().collect(Collectors.toList());
```

### 规约
```java
List<String>strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
List<String> filtered = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());
 
System.out.println("筛选列表: " + filtered);
String mergedString = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.joining(", "));
System.out.println("合并字符串: " + mergedString);
```

### 统计
```java
List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
 
IntSummaryStatistics stats = numbers.stream().mapToInt((x) -> x).summaryStatistics();
 
System.out.println("列表中最大的数 : " + stats.getMax());
System.out.println("列表中最小的数 : " + stats.getMin());
System.out.println("所有数之和 : " + stats.getSum());
System.out.println("平均数 : " + stats.getAverage());
```