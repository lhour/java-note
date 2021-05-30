package javacode.jdk动态代理.动态代理实现.provide;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class BookProvider implements InvocationHandler{

    private Object target = null;

    public BookProvider(Object target) {
        //给目标对象赋值，传入的是谁，就给谁创建代理
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        
        Object res = null;

        res = method.invoke(target, args);
        System.out.println("从代理商下单了！");
        if(res != null){
            float price = (Float)res; 
            res = price + 25;
        }
        return res;
    }
    
}
