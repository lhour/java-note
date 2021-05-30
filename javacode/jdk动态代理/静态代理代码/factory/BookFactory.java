package javacode.jdk动态代理.静态代理代码.factory;

import javacode.jdk动态代理.静态代理代码.service.*;

public class BookFactory implements BookSell{

    @Override
    public float sell_price_one(int amount) {
        return 3000 + 3000/amount;
    }

}
