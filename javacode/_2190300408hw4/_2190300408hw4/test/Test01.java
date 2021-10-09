/**
 * 
 * @Author: lhour
 * @Date: 2021-09-28 09:26:26
 * @LastEditTime: 2021-09-28 15:18:34
 * 
 * 单线程测试
 * 
 */
package _2190300408hw4.test;

import java.util.Random;

import _2190300408hw4.BankAccount;
import _2190300408hw4.mylog.Logger;

public class Test01 {
    public static void main(String[] args) {

        Random r = new Random();

        Logger.write("日志类工作正常");

        Logger.write("单线程=====测试开始");
        BankAccount acc1 = new BankAccount("小红", 10054, 1000.00);
        Logger.write(acc1.toString());

        for(int i = 0; i < 100; i ++) {
            int choose = r.nextInt();
            Double money = r.nextDouble() * 200;

            if(choose % 2 == 0){                
                acc1.deposit(money);
                Logger.write(acc1);
            }else{
                acc1.withdraw(money);
                Logger.write(acc1);
            }
        }

    }
}
