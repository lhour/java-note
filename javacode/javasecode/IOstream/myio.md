## IO流的分类

### 以流的方向做分类
向内存中去，叫输入，In，或者叫做读
从内存中出，叫输出，Out，或者写

### 按读取数据分类
1. 字节流：万能的，什么文件都可读取，图片，影视
2. 字符流：一次读一个字符，只能读取普通文本文件

### 以reader和writer相关为字符流

### input，output为字节流

## FileInputStream
```java
File f = new File("C:/java/hello");
InputStream in = new FileInputStream(f);
```
1. public void close() throws IOException{}
关闭此文件输入流并释放与此流有关的所有系统资源。抛出IOException异常。
2. protected void finalize()throws IOException {}
这个方法清除与该文件的连接。确保在不再引用文件输入流时调用其 close 方法。抛出IOException异常。
3. public int read(int r)throws IOException{}
这个方法从 InputStream 对象读取指定字节的数据。返回为整数值。返回下一字节数据，如果已经到结尾则返回-1。
4. public int read(byte[] r) throws IOException{}
这个方法从输入流读取r.length长度的字节。返回读取的字节数。如果是文件结尾则返回-1。
5. public int available() throws IOException{}
返回下一次对此输入流调用的方法可以不受阻塞地从此输入流读取的字节数。返回一个整数值。

## BufferedReader
```java
BufferedReader in
    = new BufferedReader(new FileReader("foo.in"));
```

## 从控制台读取字符串
```java
//使用 BufferedReader 在控制台读取字符
import java.io.*;
 
public class BRReadLines {
    public static void main(String[] args) throws IOException {
        // 使用 System.in 创建 BufferedReader
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str;
        System.out.println("Enter lines of text.");
        System.out.println("Enter 'end' to quit.");
        do {
            str = br.readLine();
            System.out.println(str);
        } while (!str.equals("end"));
    }
}
```