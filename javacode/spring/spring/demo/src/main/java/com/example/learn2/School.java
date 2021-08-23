package com.example.learn2;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("school")
public class School {
    
    @Value("工大")
    private String name;
    @Value("威海")
    private String add;

    public School() {
        // System.out.println(Thread.currentThread().getStackTrace()[1].getClassName() + "无参");
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAdd(String add) {
        this.add = add;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return Thread.currentThread().getStackTrace()[1].getClassName()
         + " + " + this.name
          + " + " + this.add;
    }

}
