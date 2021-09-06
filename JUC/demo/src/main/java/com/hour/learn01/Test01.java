package com.hour.learn01;

public class Test01 {
    public static void main(String[] args) {
        //获取 CPU 的核数
        //CPU 密集型 IO密集型
        System.out.println(Runtime.getRuntime().availableProcessors());

    }
}
