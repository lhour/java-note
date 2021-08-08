package jdk动态代理.静态代理代码.provide;
import jdk动态代理.静态代理代码.factory.BookFactory;
import jdk动态代理.静态代理代码.service.BookSell;

public class Taobao implements BookSell{

    //代理厂家是谁
    private BookFactory win = new BookFactory(); 

    @Override
    public float sell_price_one(int amount) {
        // 向厂家发送订单,告诉厂家买多少

        //原有功能
        float in_price = win.sell_price_one(amount);

        //增强功能
        float out_price = in_price + 1000;

        return out_price;
    }
    
}
