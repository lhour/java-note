package jdk动态代理.javaMethod;

import java.lang.reflect.*;

import jdk动态代理.javaMethod.service.Helloservice;
import jdk动态代理.javaMethod.service.impl.Helloimpl;
import jdk动态代理.javaMethod.service.impl.Helloimpl2;

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
        //代理时，重写invoke方法就行了，不用动原方法，实现便捷化
        Helloservice hello2 = new Helloimpl2();
        sayhello2.invoke(hello2, "hiter");
        sayhello2.invoke(target, "Chinese");
    }   
}
