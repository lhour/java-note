package com.example.learn2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * 
 * 创建对象，等同于 <bean> 的功能
 * value：对象名称，仅一个
 *     spring中仅一个
 * 
 * @Component(value = "myStudent")
 * 等同于 <bean id = "myStudent" class = "com.example.learn1.Student" />
 * 
 * 相用于对象创建的
 * @Repository:用在dao层，就是连接数据库的持久层
 * @Service：放在service类上，创建service对象有事务功能
 * @Controller:控制器对象，接受用户提交的参数，显示请求的结果
 * 
 * 实现程序分层
 */
@Component(value = "myStudent2")
public class Student {
    
    /**
     * @Value:String类型，用于赋值
     *      1.在属性上方，无需set
     *      2.在set方法上
     */    
    @Value("张三")
    private String name;
    @Value("19")
    private Integer age;

    /**
     * @Autowired,实现引用类型注入 byType
     * 
     *  1.属性上，无需set
     *  2.set方法上
     */    

    @Autowired
    @Qualifier(value = "school")
    School school;

    public Student(){
        // System.out.println(Thread.currentThread().getStackTrace()[1].getClassName() + "无参");
    }

    // public void setName(String name) {
    //     this.name = name;
    // }

    // public void setAge(Integer age) {
    //     this.age = age;
    // }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return Thread.currentThread().getStackTrace()[1].getClassName()
         + " + " + this.name
          + " + " + this.age
           + " + " + this.school.toString();
    }
}
