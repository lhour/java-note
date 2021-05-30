在javacode.jdk动态代理.javaMethod中,
有
```java
public class Test1 {
    
    public static void main(String args[]) throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        //原来的方法，但是没有加代理！！
        //如果要加功能，只能在helloimpl中方法写，不同的功能就需要不同的类
        Helloservice hello = new Helloimpl();
        hello.sayHello("world");

        //通过方法,核心Method
        Helloservice target = new Helloimpl();
        //获取sayhello名称对应的Method对象
        Method sayhello2 = Helloservice.class.getMethod("sayHello", String.class);

        //通过Method执行方法  

        Helloservice hello2 = new Helloimpl2();
        sayhello2.invoke(hello2, "hiter");
        sayhello2.invoke(target, "Chinese");
    }   
}
```
第一种输出
```java
    Helloservice hello = new Helloimpl();
    hello.sayHello("world");
```

第二种
```java
    //通过方法,核心Method
    Helloservice target = new Helloimpl();
    //获取sayhello名称对应的Method对象
    Method sayhello2 = Helloservice.class.getMethod("sayHello", String.class);
    sayhello2.invoke(target, "Chinese");
```
1. 为什么用第二种，不用第一种，为什么反射？
2. 首先，sayhello是一个方法，这个方法可以是连接mysql数据库的
3. 那么就会有一个sayhi，是连接mycat的

4. 如果要交换数据库，第一种是不行的，因为我们一般都是本地编译好class文件
5. 要变更就得再次编译一下，再传回服务器，在运行，这是不行的！
6. 而用第二种，我们可以把“sayhello”写到一个xml文件中，再另写一个函数，每次使用数据库操作时，或者每次更换数据库操作时，就检测一下xml是否更换
7. 这样就做到，不用重新编译class
8. 为什么反射？因为jvm运行会加载你写的类，写的方法，new的对象，不用反射就是在一开始就全加载了，而用来反射就是用那个加载那个


