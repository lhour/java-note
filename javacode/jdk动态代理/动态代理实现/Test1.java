package javacode.jdk动态代理.动态代理实现;

import java.lang.reflect.Proxy;

import javacode.jdk动态代理.动态代理实现.factory.BookFactory;
import javacode.jdk动态代理.动态代理实现.provide.BookProvider;
import javacode.jdk动态代理.动态代理实现.service.Booksell;

public class Test1{
    public static void main(String[] args) {
        //创建基础的对象
        Booksell factory = new BookFactory();
        
        //如果要改变增强方法，就要改变这个类
        BookProvider pvd = new BookProvider(factory);

        //创建代理对象
        Booksell proxy = (Booksell)Proxy.newProxyInstance(factory.getClass().getClassLoader(),
                        factory.getClass().getInterfaces(),
                        pvd);

        //代理执行，既完成任务，比如输出日志，完成程序之类
        //又完成了增强，比如计算时间，开始和结束时抛出日志
        float price = proxy.sell(1);
        System.out.println(price);
    }
}
