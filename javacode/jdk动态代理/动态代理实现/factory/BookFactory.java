package jdk动态代理.动态代理实现.factory;

import jdk动态代理.动态代理实现.service.Booksell;

public class BookFactory implements Booksell{

    /**
     * @param {*}单次购买数量
     * @return {price}单价
     */ 
    @Override
    public float sell(int mount) {

        float price = 3000 + 3000/mount + 3000/(mount*mount);
        System.out.println("从厂商下单了！");
        return price;
    }
    
    
}
