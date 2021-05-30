package javacode.jdk动态代理.javaMethod.service.impl;

import javacode.jdk动态代理.javaMethod.service.Helloservice;

public class Helloimpl implements Helloservice{

    @Override
    public void sayHello(String name) {
        
        System.out.println("hello "+ name + "!");
    }

}