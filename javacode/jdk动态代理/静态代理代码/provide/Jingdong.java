package javacode.jdk动态代理.静态代理代码.provide;

import javacode.jdk动态代理.静态代理代码.factory.BookFactory;
import javacode.jdk动态代理.静态代理代码.service.BookSell;

public class Jingdong implements BookSell {

    //代理厂家是谁
    private BookFactory win = new BookFactory(); 

    @Override
    public float sell_price_one(int amount) {
        // 向厂家发送订单,告诉厂家买多少
        if(amount <= 2000){
            amount = 2000;
        }else{
            amount = 10000;
        }
        //原有功能
        float in_price = win.sell_price_one(amount);

        //增强功能
        float out_price = in_price + 500;

        return out_price;
    }
    
}
