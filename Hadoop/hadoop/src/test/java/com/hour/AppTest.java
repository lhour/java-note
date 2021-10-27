package com.hour;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * Unit test for simple App.
 */
class AppTest {
    /**
     * Rigorous Test.
     */
    @Test
    public void writeToHDFS() throws IOException {
        
        System.setProperty("HADOOP_USER_NAME", "root");
        //新建配置文件
        Configuration conf = new Configuration();
        //给配置文件设置HDFS文件的默认入口
        conf.set("fs.defaultFS", "hdfs://192.168.163.11:9000");
        //传入参数
        FileSystem fs = FileSystem.get(conf);

        Path p = new Path("/1.txt");
        //输出流
        FSDataOutputStream fos = fs.create(p, true, 1024);
        fos.write("hello world!".getBytes());
        fos.close();
    }

    @Test
    public void readToHDFS() throws IOException {
        System.setProperty("HADOOP_USER_NAME", "root");
        //新建配置文件
        Configuration conf = new Configuration();
        //给配置文件设置HDFS文件的默认入口
        conf.set("fs.defaultFS", "hdfs://192.168.163.11:9000");
        //传入参数
        FileSystem fs = FileSystem.get(conf);

        Path p = new Path("/1.txt");
        //输出流
        FSDataInputStream fis = fs.open(p);
        
        byte[] buf = new byte[1024];

        int len = 0;

        while((len = fis.read(buf)) != -1) {
            System.out.print(new String(buf, 0, len));
        }
        fis.close();
    }
}
