package com.example;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.example.aspect1.*;

public class test3 {
    
    @Test
    public void test(){
        String config = "applicationContext.xml";
        ApplicationContext ac = new ClassPathXmlApplicationContext(config);
        SomeService proxy = (SomeService) ac.getBean("someService");
        proxy.doSome("siri", 18);

        ((ClassPathXmlApplicationContext) ac).close();
    }
}
