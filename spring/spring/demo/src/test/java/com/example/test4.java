package com.example;

import com.example.mybatis.service.StudentService;

import java.util.List;
// import java.util.stream.Stream;
import com.example.mybatis.domin.Student;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
// import org.springframework.jdbc.datasource.DataSourceTransactionManager;
// import org.mybatis.spring.SqlSessionFactoryBean;
public class test4 {
    
    @Test
    public void test(){
        String config = "applicationContext.xml";
        ApplicationContext ac = new ClassPathXmlApplicationContext(config);
        String[] names = ac.getBeanDefinitionNames();

        for (String s : names){
            System.out.println(ac.getBean(s).getClass().getName());
        }

        StudentService ss =(StudentService) ac.getBean("studentService");
        List<Student> s = ss.quartStudents();
        s.stream().limit(5)
                    .forEach(System.out :: println);
        ((ClassPathXmlApplicationContext) ac).close(); 
    }
}

