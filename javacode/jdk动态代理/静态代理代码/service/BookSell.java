package jdk动态代理.静态代理代码.service;
/*
 * @Author: lhour
 * @Date: 2021-05-28 22:04:16
 * @LastEditTime: 2021-08-08 20:09:40
 * @LastEditors: lhour
 * @Description: change me
 * @FilePath: \javacode\jdk动态代理\静态代理代码\service\BookSell.java
 * 
 *模拟购买笔记本
    * 定义一个接口,定义卖笔记本的方法,表示厂家做的事
    * 创建厂家类
    * 创建商家,代理商
    * 创建客户端
 */

public interface BookSell{

   /**
    * @description: 
    * @param {int} amount
    * @return {*} 购买 amount 个笔记本时的单价
    */   
   float sell_price_one(int amount);
}
