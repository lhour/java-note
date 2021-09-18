## ServletConfig对象有什么用？
通过此对象可以读取web.xml中配置的初始化参数。
好处就是：能够让你的程序更加灵活【更换需求，更改配置文件web.xml即可，程序代码不用改】

## ServletContext有什么用？
1. ServletContext既然代表着当前web站点，那么所有Servlet都共享着一个ServletContext对象，所以Servlet之间可以通过ServletContext实现通讯。
2. ServletConfig获取的是配置的是单个Servlet的参数信息，ServletContext可以获取的是配置整个web站点的参数信息
3. 利用ServletContext读取web站点的资源文件
4. 实现Servlet的转发【用ServletContext转发不多，主要用request转发】

### Servlet之间实现通讯
ServletContext对象可以被称之为域对象

到这里可能有一个疑问，域对象是什么呢？其实域对象可以简单理解成一个容器【类似于Map集合】

实现Servlet之间通讯就要用到ServletContext的setAttribute(String name,Object obj)方法
第一个参数是关键字
第二个参数是你要存储的对象
```java
//获取到ServletContext对象
ServletContext servletContext = this.getServletContext();        
String value = "zhongfucheng";        
//MyName作为关键字，value作为值存进   域对象【类型于Map集合】
servletContext.setAttribute("MyName", value);
```

```java
//获取ServletContext对象        
ServletContext servletContext = this.getServletContext();        
//通过关键字获取存储在域对象的值       
String value =(String) servletContext.getAttribute("MyName");
System.out.println(value);
```

### 获取web站点配置的信息
如果我想要让所有的Servlet都能够获取到连接数据库的信息，
不可能在web.xml文件中每个Servlet中都配置一下，这样代码量太大了！

web.xml文件支持对整个站点进行配置参数信息【所有Servlet都可以取到该参数信息】

### 读取资源文件
#### 1
我们以前读取文件的时候，如果程序和文件在同一包名，可以直接通过文件名称获取得到的！，
原因很简单，以前我们写的程序都是通过JVM来运行的，而现在，我们是通过Tomcat来运行的

根据web的目录规范，Servlet编译后的class文件是存放在WEB-INF\classes文件夹中的

读取文件的时候都要写上绝对路径，太不灵活了。
试想一下，如果我将该读取文件的模块移到其他的web站点上，我的代码就又要修改了【因为web站点的名字不一样】。

我们通过ServletContext读取就可以避免修改代码的情况，
因为ServletContext对象是根据当前web站点而生成的

```java
//获取到ServletContext对象
        
ServletContext servletContext = this.getServletContext();        
//调用ServletContext方法获取到读取文件的流        
InputStream inputStream 
    = servletContext.getResourceAsStream("/WEB-INF/classes/zhongfucheng/web/1.png");
```

#### 2
如果我的文件放在web目录下，那么就简单得多了！,直接通过文件名称就能获取
```java
//获取到ServletContext对象
ServletContext servletContext = this.getServletContext();
//调用ServletContext方法获取到读取文件的流        
InputStream inputStream = servletContext.getResourceAsStream("2.png");
```

#### 3
通过类装载器读取资源文件。
文件放在了src目录下【也叫做类目录】
```java
//获取到类装载器    
ClassLoade classLoader = Servlet111.class.getClassLoader();        
//通过类装载器获取到读取文件流        
InputStream inputStream = classLoader.("/zhongfucheng/web/1.png");
```
如果文件太大，就不能用类装载器的方式去读取，会导致内存溢出


