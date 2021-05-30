package javacode.jdk动态代理.动态代理实现;

import java.lang.reflect.Proxy;

import javacode.jdk动态代理.动态代理实现.factory.BookFactory;
import javacode.jdk动态代理.动态代理实现.provide.BookProvider;
import javacode.jdk动态代理.动态代理实现.service.Booksell;

public class Test1{
    public static void main(String[] args) {
        //创建基础的对象
        Booksell factory = new BookFactory();
        
        BookProvider pvd = new BookProvider(factory);

        //创建代理对象
        Booksell proxy = (Booksell)Proxy.newProxyInstance(factory.getClass().getClassLoader(),
                        factory.getClass().getInterfaces(),
                        pvd);

        float price = proxy.sell(1);
        System.out.println(price);
    }
}
