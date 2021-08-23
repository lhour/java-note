## 一个已经注册的Servlet可以被多次映射
```xml

<servlet>           
    <servlet-name>Demo1</servlet-name>           
    <servlet-class>zhongfucheng.web.Demo1</servlet-class>       
</servlet>
    
<servlet-mapping>          
    <servlet-name>Demo1</servlet-name>         
    <url-pattern>/Demo1</url-pattern>
</servlet-mapping>
    
<servlet-mapping>           
    <servlet-name>Demo1</servlet-name>         
    <url-pattern>/ouzicheng</url-pattern>        
</servlet-mapping>
```

## Servlet映射的URL可以使用通配符
通配符有两种格式：

1. *.扩展名
2. 正斜杠（/）开头并以“/*”结尾。

扩展名和正斜杠（/）开头并以“/”结尾两种通配符同时出现

1. 看谁的匹配度高，谁就被选择
2. *.扩展名的优先级最低

## Servlet是单例的

### 为什么Servlet是单例的
浏览器多次对Servlet的请求，一般情况下，服务器只创建一个Servlet对象
Servlet对象一旦创建了，就会驻留在内存中，为后续的请求做服务，直到服务器关闭。

### 每次访问请求对象和响应对象都是新的
对于每次访问请求
Servlet引擎都会创建一个新的HttpServletRequest请求对象和一个新的HttpServletResponse响应对象
然后将这两个对象作为参数传递给它调用的Servlet的service()方法
service方法再根据请求方式分别调用doXXX方法。

### 线程安全问题
当多个用户访问Servlet的时候，服务器会为每个用户创建一个线程。
当多个用户并发访问Servlet共享资源的时候就会出现线程安全问题。
原则：
1. 如果一个变量需要多个用户共享，则应当在访问该变量的时候，加同步机制synchronized (对象){}
2. 如果一个变量不需要共享，则直接在 doGet() 或者 doPost()定义.这样不会存在线程安全问题

### load-on-startup
如果在元素中配置了一个元素，那么WEB应用程序在启动时，
就会装载并创建Servlet的实例对象、
以及调用Servlet实例对象的init()方法。

作用：
1. 为web应用写一个InitServlet，这个servlet配置为启动时装载，为整个web应用创建必要的数据库表和数据
2. 完成一些定时的任务【定时写日志，定时备份数据】


