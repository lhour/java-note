package javacode.jdk动态代理.动态代理实现.service;

//功能接口
public interface Booksell {
    
    /**
     * @param {int} 一次购买的数量
     * @return {price} 本次的单价
     */    
    float sell(int mount);
}
