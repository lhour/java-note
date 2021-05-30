package javacode.jdk动态代理.动态代理code.service.impl;

import javacode.jdk动态代理.动态代理code.service.Helloservice;

public class Helloimpl implements Helloservice{

    @Override
    public void sayHello(String name) {
        
        System.out.println("hello "+ name + "!");
    }

}