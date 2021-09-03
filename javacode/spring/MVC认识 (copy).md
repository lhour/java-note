## 1.2 SpringMVC 优点
### 1. 基于 MVC 架构
基于 MVC 架构，功能分工明确。解耦合，
### 2. 容易理解，上手快；使用简单。
就可以开发一个注解的 SpringMVC 项目，SpringMVC 也是轻量级的，jar 很小。不依赖
特定的接口和类。
### 3. 作 为 Spring 框 架 一 部 分 ， 能 够 使 用 Spring 的 IoC  。 方 便 整 合
Strtus,MyBatis,Hiberate,JPA 等 等 其他 框架。 。
### 4.SpringMVC 强化注解的使用，在控制器，Service ，Dao 都可以使用注解 。方便灵活。
使用@Controller 创建处理器对象,@Service 创建业务对象，@Autowired 或者@Resource
在控制器类中注入 Service, Service 类中注入 Dao。

SpringMVC就是一个Spring。 Spring是容器，ioc能够管理对象，使用<bean>, 
@Component, @Repository, @Service, @Controller
SpringMVC能够创建对象， 放入到容器中（SpringMVC容器），
springmvc容器中放的是控制器对象，

我们要做的是 使用@Contorller创建控制器对象， 把对象放入到springmvc容器中， 把创建的对象作为控制器使用
这个控制器对象能接收用户的请求，显示处理结果，就当做是一个servlet使用。

使用@Controller注解创建的是一个普通类的对象，不是Servlet。 springmvc赋予了控制器对象一些额外的功能。

web开发底层是servlet， springMVC中有一个对象是Servlet ： DispatherServlet(中央调度器)
DispatherServlet: 负责接收用户的所有请求， 用户把请求给了DispatherServlet， 之后DispatherServlet把请求转发给