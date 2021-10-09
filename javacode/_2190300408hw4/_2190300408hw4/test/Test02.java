package _2190300408hw4.test;

import java.util.Random;
import _2190300408hw4.BankAccount;
import _2190300408hw4.mylog.Logger;

public class Test02 {
    
    public static void main(String[] args) {

        Random r = new Random();
        BankAccount ac1 = new BankAccount("小明", 10055, 0.00);
        Logger.write(ac1.toString());

        Logger.write("多线程==========测试开始");

        new Thread(
            () -> {
                for(int i = 0; i < 10; i ++) {
                    Double money = r.nextDouble() * 200;
                    synchronized(ac1){
                        ac1.deposit(money);
                        Logger.write(ac1);
                    }
                }
        },"小明").start();

        new Thread(
            () -> {
                for(int i = 0; i < 10; i ++) {
                    Double money = r.nextDouble() * 200;
                    synchronized(ac1){
                        ac1.withdraw(money);
                        Logger.write(ac1);
                    }
                }
        },"大伟").start();

    }
}
