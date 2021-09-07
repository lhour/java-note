package com.example.aspect1;

import org.springframework.stereotype.Component;

@Component("someService")
public class SomeServiceImp implements SomeService{

    @Override
    public String doSome(String s, Integer i) {
        // 输出执行时间
        System.out.println("======目标方法doSome执行======");
        
        return "aaa";
    }
    
}
