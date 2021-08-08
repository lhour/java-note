
/*
 * @Author: lhour
 * @Date: 2021-08-08 14:52:34
 * @LastEditTime: 2021-08-08 20:16:15
 * @LastEditors: lhour
 * @Description: 实现线程的第三种方式
 * @FilePath: \javacode\javasecode\Thread\ImpTHIRD.java
 */
package javasecode.Thread;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class ImpTHIRD implements Callable {
    public static void main(String[] args) throws Exception {

        //定义任务
        FutureTask task = new FutureTask((Callable) new Callable<Object>() {
            @Override
            public Object call() throws Exception{
                System.out.println("begin");
                Thread.sleep(1000 * 3);
                System.out.println("end");
                int a = 100;
                int b = 200;
                return a + b;
            }
        });
        //新建线程
        Thread t = new Thread(task);
        //启动
        t.start();
        //获取结果
        Object obj = task.get();
        //主线程阻塞，等待结果
        System.out.println(obj);

        System.out.println("main");
    }

    @Override
    public Object call() throws Exception {
        // TODO Auto-generated method stub
        return null;
    }
    
}
