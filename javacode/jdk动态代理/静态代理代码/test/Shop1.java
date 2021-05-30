package javacode.jdk动态代理.静态代理代码.test;

import javacode.jdk动态代理.静态代理代码.provide.Jingdong;
import javacode.jdk动态代理.静态代理代码.provide.Taobao;

public class Shop1 {

    public static void main(String args[]) {
        Taobao taobao = new Taobao();
        float price1 = taobao.sell_price_one(2500);
        System.out.println("通过淘宝购买的单价为"+price1);

        Jingdong jingdong = new Jingdong();
        float price2 = jingdong.sell_price_one(2500); 
        System.out.println("通过京东购买的单价为"+price2);
    }
}
