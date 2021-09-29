package _2190300408hw4.mylog;

import java.util.Date;
import java.text.SimpleDateFormat;

public class Logger {

    /**
     * 输出示例：
     * [ 2021-09-27 11:58:48 0954 ]  线程：main out: 日志记录器
     * @param 日志内容
     */
    public static void write(String s) {
        s = "[ " + new SimpleDateFormat("yyyy-MM-dd hh:mm:ss SSSS").format(new Date()) + 
                " ]  线程：" + Thread.currentThread().getName() + " out: " + s;

        System.out.println(s);
    }

}