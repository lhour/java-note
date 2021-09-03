## IOC控制翻转
* 就是把对象创建、管理等交给框架来完成，而不是程序员。
* spring核心是一个容器，在我们写的代码之外，管理对象
* 控制和翻转
  * 控制：对象的创建，对象之间的关系
  * 翻转：
  * 相对于：开发人员用new创建对象
* 容器：一个软件，一个框架
* 使用ioc的好处：改动很少的代码，就可以实现不同的功能。实现解耦合。在容器中更改就可

#### 创建对象的方式
1. 构造方法
2. 反射
3. 序列化
4. 克隆
5. ioc；容器创建
   1. servlet：
      1. 创建类继承HttpServlet
      2. 在web.xml中注册servlet，使用servlet

## xml中的beans

### 
```xml
    <bean id="..." class="...">
       <!-- bean 配置 -->
    </bean>
    
    <!-- App bean -->
    <bean id="app" class="App" lazy-init="true"></bean>
```
id: bean的唯一标识符
class: 指定bean的Java类名称
lazy-init: 懒加载，让Spring容器在Bean首次用到时创建Bean实例，而不是在应用一启动就创建

1. constructor-arg标记:它是bean标记的子标记，用以传入构造参数进行实例化，这也是注入依赖关系的一种常见方式。
2. index属性指定构造参数的序号（从0开始），当只有一个构造参数是通常省略不写；
3. type属性指定构造参数的类型，当为基本数据类型时亦可省略此属性；参数值可通过ref属性或value属性直接指定，
也可以通过ref或value子标记指定
4. property:它是bean标记的子标记，用以调用Bean实例中的相关Set方法完成属性值的赋值，从而完成依赖关系的注入
5. name:知道bean实例的属性名称
6. ref标记:该标记通常作为constructor-arg、property、list、set、entry等标记的子标记，
由bean属性指定一个Bean工厂中某个Bean实例的引用
7. value标记：该标记通常作为constructor-arg、property、list、set、entry等标记的子标记，用以直接指定一个常量值
8. list标记:该标记用以封装List或数组类型属性的依赖注入（即赋值），具体的元素通过ref或value子标记
9. set标记：该标记用以封装Set类型属性的依赖注入（即赋值），具体的元素通过ref或value子标记指定
10. map标记:标记用以封装Map类型属性的依赖注入（即赋值），具体的元素通过entry子标记指定
11. entry标记：该标记通常用做map标记的子标记，用以设置一个“键/值”对。key属性接收字符串类型的“键”名称，
“值”则可由ref或value子标记指定
12. props标记:
该标记用以封装Properties类型属性的依赖注入（即赋值），具体的元素通过prop子标记指定
13. prop标记:该标记通常用做props标记的子标记，用以设置一个“键/值”对。key属性接收字符串类型的“键”名称，
“值”则可放置在prop标记体内
14. null标记：该标记用于赋一个null值，与在Java中直接为某个属性赋null值效果等同

```xml
  <bean id="user" class="test.spring.bean.User">
    <property name="name" value="小明"/>
    <property name="password" value="123456"/>
  </bean>
```



