package com.example;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.example.learn1.Student;

class AppTest {
    
    @Test
    void test01() {
        
        try {
            String config = "applicationContext.xml";
            ApplicationContext apc = new ClassPathXmlApplicationContext(config);
            Student stu = (Student)apc.getBean("student");
            // Student stu2 = (Student)apc.getBean("myStudent");
            // ((ClassPathXmlApplicationContext) apc).close();
            System.out.println(stu.toString());
            // System.out.println("1");
            // System.out.println(stu2.toString());  
            // 结果一样，同一个对象
        }catch(Exception e){
            System.out.println(e);
        }
    }
}
