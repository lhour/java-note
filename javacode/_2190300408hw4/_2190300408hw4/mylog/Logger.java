package _2190300408hw4.mylog;

import java.util.Date;
import java.text.SimpleDateFormat;

public class Logger {

    /**
     * 实际会写在一个配置文件中
     * 日志等级等级
     */
    private static int GRADE = 2;

    /**
     * 输出示例：
     * [ 2021-09-27 11:58:48 0954 WRITE ]  线程：main out: 日志记录器
     * @param 日志内容
     */
    public static void write(Object o) {
        String s = o.toString();
        s = "[ " + new SimpleDateFormat("yyyy-MM-dd hh:mm:ss SSSS").format(new Date()) + 
                " WRITE ]  线程：" + Thread.currentThread().getName() + " out: " + s;

        System.out.println(s);
    }

    /**
     * 通知
     * @param 日志信息
     */
    public static void info(Object o) {
        String s = o.toString();
        s = "[ " + new SimpleDateFormat("yyyy-MM-dd hh:mm:ss SSSS").format(new Date()) + 
                " INFO ]  线程：" + Thread.currentThread().getName() + " out: " + s;
        if(GRADE < 2) return; 
        System.out.println(s);
    }

    /**
     * 警告
     * @param 日志信息
     */
    public static void warn(Object o) {
        String s = o.toString();
        s = "[ " + new SimpleDateFormat("yyyy-MM-dd hh:mm:ss SSSS").format(new Date()) + 
                " WARN ]  线程：" + Thread.currentThread().getName() + " out: " + s;
        if(GRADE < 1) return;
        System.out.println(s);
    }

    /**
     * 错误
     * @param 日志信息
     */
    public static void error(Object o) {
        String s = o.toString();
        s = "[ " + new SimpleDateFormat("yyyy-MM-dd hh:mm:ss SSSS").format(new Date()) + 
                " ERROR ]  线程：" + Thread.currentThread().getName() + " out: " + s;
        if(GRADE < 0) return;
        System.out.println(s);
    }


}