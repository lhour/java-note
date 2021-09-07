package com.example.aspect1;

import java.util.Date;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * AspectJ中的注解，表明是切面类
 * 用于增强功能
 */
@Aspect
@Component("myAspect")
public class MyAspect {
    /**
     * 方法用于实现切面功能
     * 公共方法public
     * 没有返回值
     * 名称自定义
     * 可以有参数，但不是自定义的，有几个参数类型可以使用
     * 
     */    

    /**
     * @Before 前置通知注解
     * 特点：目标方法之前执行
     *      不会改变目标方法的执行结果
     *      不会影响目标执行
     */     
    @Before(value = "execution(public String com.example.aspect1.SomeServiceImp.doSome(String, Integer))")
    public void myBefore() {
        System.out.println("【前置通知】切面功能：在目标方法之前输出 ：" + new Date());
    }

    @Before(value = "execution(* *..SomeServiceImp.doSome(..))")
    public void myBefore2(JoinPoint jPoint) {
        
        System.out.println("【前置通知】【表达式更多写法】" + jPoint.getSignature());
        System.out.println("【前置通知】【表达式更多写法】" + jPoint.getSignature().getName());
        Object[] args = jPoint.getArgs();
        for(Object arg : args){
            System.out.println("【前置通知】【表达式更多写法】" + arg);
        }
        

        System.out.println("【前置通知】【表达式更多写法】切面功能：在目标方法之前输出 ：" + new Date());
    }

    @Before(value = "mypt()")
    public void myBefore3() {
        System.out.println("【前置通知】【mypt()】切面功能：在目标方法之前输出 ：" + new Date());
    }

    @AfterReturning(value = "execution(* *..SomeServiceImp.doSome(..))",
                        returning = "res")
    public void myReturning(Object res) {
        System.out.println("【后置通知】返回值为：" + res);
    }

    @Pointcut(value = "execution(* *..SomeServiceImp.doSome(..))")
    public void mypt(){

    }
}
