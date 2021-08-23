## 以下是lambda表达式的重要特征:

1. 可选类型声明：不需要声明参数类型，编译器可以统一识别参数值。
2. 可选的参数圆括号：一个参数无需定义圆括号，但多个参数需要定义圆括号。
3. 可选的大括号：如果主体包含了一个语句，就不需要使用大括号。
4. 可选的返回关键字：如果主体只有一个表达式返回值则编译器会自动返回值，大括号需要指定明表达式返回了一个数值。

## 举例

// 1. 不需要参数,返回值为 5  
() -> 5  
  
// 2. 接收一个参数(数字类型),返回其2倍的值  
x -> 2 * x  
  
// 3. 接受2个参数(数字),并返回他们的差值  
(x, y) -> x – y  
  
// 4. 接收2个int型整数,返回他们的和  
(int x, int y) -> x + y  
  
// 5. 接受一个 string 对象,并在控制台打印,不返回任何值(看起来像是返回void)  
(String s) -> System.out.print(s)

## 变量作用域
lambda 表达式只能引用标记了 final 的外层局部变量，
这就是说不能在 lambda 内部修改定义在域外的局部变量，否则会编译错误。
```java
public class Java8Tester {
 
   final static String salutation = "Hello! ";
   
   public static void main(String args[]){
      GreetingService greetService1 = message -> 
      System.out.println(salutation + message);
      greetService1.sayMessage("Runoob");
   }
    
   interface GreetingService {
      void sayMessage(String message);
   }
}
```

## lambdas 实现 Runnable 接口
```java
// 1.1使用匿名内部类  
new Thread(new Runnable() {  
    @Override  
    public void run() {  
        System.out.println("Hello world !");  
    }  
}).start();  
  
// 1.2使用 lambda expression  
new Thread(() -> System.out.println("Hello world !")).start();  
  
// 2.1使用匿名内部类  
Runnable race1 = new Runnable() {  
    @Override  
    public void run() {  
        System.out.println("Hello world !");  
    }  
};  
  
// 2.2使用 lambda expression  
Runnable race2 = () -> System.out.println("Hello world !");  
   
// 直接调用 run 方法(没开新线程哦!)  
race1.run();  
race2.run();  
```

## 使用 Lambdas 排序集合
```java
String[] players = {"Rafael Nadal", "Novak Djokovic",   
    "Stanislas Wawrinka", "David Ferrer",  
    "Roger Federer", "Andy Murray",  
    "Tomas Berdych", "Juan Martin Del Potro",  
    "Richard Gasquet", "John Isner"};  
   
// 1.1 使用匿名内部类根据 name 排序 players  
Arrays.sort(players, new Comparator<String>() {  
    @Override  
    public int compare(String s1, String s2) {  
        return (s1.compareTo(s2));  
    }  
});  
```

```java
// 1.2 使用 lambda expression 排序 players  
Comparator<String> sortByName = (String s1, String s2) -> (s1.compareTo(s2));  
Arrays.sort(players, sortByName);  
  
// 1.3 也可以采用如下形式:  
Arrays.sort(players, (String s1, String s2) -> (s1.compareTo(s2)));  
```

