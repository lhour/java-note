## **一、前言**

众所周知，spring最核心的两个功能是aop和ioc，即面向切面和控制反转。本文会讲一讲SpringBoot如何使用AOP实现面向切面的过程原理。

## **二、何为aop**

 aop全称`Aspect Oriented Programming`，面向切面，AOP主要实现的目的是针对业务处理过程中的切面进行提取，它所面对的是处理过程中的某个步骤或阶段，以获得逻辑过程中各部分之间低耦合性的隔离效果。其与设计模式完成的任务差不多，是提供另一种角度来思考程序的结构，来弥补面向对象编程的不足。

　　通俗点讲就是提供一个为一个业务实现提供切面注入的机制，通过这种方式，在业务运行中将定义好的切面通过切入点绑定到业务中，以实现将一些特殊的逻辑绑定到此业务中。

　　举个栗子，项目中有记录操作日志的需求、或者流程变更是记录变更履历，无非就是插表操作，很简单的一个save操作，都是一些记录日志或者其他辅助性的代码。一遍又一遍的重写和调用。不仅浪费了时间，又将项目变得更加的冗余，实在得不偿失。

　　所以就需要面向切面aop就出场了。

## **三、aop相关名词**

 要理解SpringBoot整合aop的实现，就必须先对面向切面实现的一些aop的名称有所了解，不然也是云里雾里。

- **切面（Aspect）**：一个关注点的模块化。以注解@Aspect的形式放在类上方，声明一个切面。
- **连接点（Joinpoint）**：在程序执行过程中某个特定的点，比如某方法调用的时候或者处理异常的时候都可以是连接点。
- **通知（Advice）**：通知增强，需要完成的工作叫做通知，就是你写的业务逻辑中需要比如事务、日志等先定义好，然后需要的地方再去用。 主要包括5个注解：Before，After，AfterReturning，AfterThrowing，Around。 @Before：在切点方法之前执行。 @After：在切点方法之后执行 @AfterReturning：切点方法返回后执行 @AfterThrowing：切点方法抛异常执行 @Around：属于环绕增强，能控制切点执行前，执行后，用这个注解后，程序抛异常，会影响@AfterThrowing这个注解
- **切点（Pointcut）**：其实就是**筛选出的连接点**，匹配连接点的断言，一个类中的所有方法都是连接点，但又不全需要，**会筛选出某些作为连接点做为切点**。如果说通知定义了切面的动作或者执行时机的话，切点则定义了执行的地点。切入点表达式如何和连接点匹配是AOP的核心：Spring缺省使用AspectJ切入点语法。
- **引入（Introduction）**：在不改变一个现有类代码的情况下，为该类添加属性和方法,可以在无需修改现有类的前提下，让它们具有新的行为和状态。其实就是把切面（也就是新方法属性：通知定义的）用到目标类中去。
- **目标对象（Target Object）**：被一个或者多个切面所通知的对象。也被称做被通知（adviced）对象。既然Spring AOP是通过运行时代理实现的，这个对象永远是一个被代理（proxied）对象。
- **AOP代理（AOP Proxy）**：AOP框架创建的对象，用来实现切面契约（例如通知方法执行等等）。在Spring中，AOP代理可以是JDK动态代理或者CGLIB代理。
- **织入（Weaving）**：把切面连接到其它的应用程序类型或者对象上，并创建一个被通知的对象。这些可以在编译时（例如使用AspectJ编译器），类加载时和运行时完成。Spring和其他纯Java AOP框架一样，在运行时完成织入。

​       其中重要的名词有：**切面（Aspect）**，**切入点（Pointcut）**

## **四、代码实现**

以处理业务逻辑日志为例，新增日志处理的面向切面处理。

### **1.添加maven依赖**

```javascript
<!--引入AOP依赖-->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-aop</artifactId>
    <version>2.1.6.RELEASE</version>
</dependency>
```

### **2.添加系统日志注解**

```javascript
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLog {
    String value() default "";
}
```

### **3.添加系统日志实体类**

```javascript
@Data
public class SysLogEntity {
    private String className;
    private String methodName;
    private String params;
    private Long exeuTime;
    private String remark;
    private String createDate;
}
```

### **4.新增Service逻辑处理层**

```javascript
@Slf4j
@Service
public class SysLogService {
    public boolean save(SysLogEntity sysLogEntity){
        // 这里就不做具体实现了
        log.info(sysLogEntity.getParams());
        return true;
    }
}
```

此处用了@Slf4j日志注解

相当于private  final Logger logger = LoggerFactory.getLogger(XXX.class);

简化了代码，怎么简化怎么来~

这里主要是研究aop的怎么实现的，就不具体写Service层的代码了。

### **5.Controller层**

```javascript
@RestController
@RequestMapping("/aop")
public class AopController {
    @SysLog("测试")
    @GetMapping("/test")
    public String test(@RequestParam("name") String name, @RequestParam("age") int age){
        return name + ", " + age;
    }
}
```

### **6.切面处理**

来咯来咯，关键处理来咯，客官下面的代码

首先在类上方声明

```javascript
@Aspect //使用@Aspect
@Component
```

调用Service服务

```javascript
@Autowired
private SysLogService sysLogService;
```

添加切点表达式@Pointcut

```javascript
/**
 * 这里我们使用注解的形式
 * 当然，我们也可以通过切点表达式直接指定需要拦截的package,需要拦截的class 以及 method
 * 切点表达式:   execution(...)
 */
@Pointcut("@annotation(com.niaobulashi.anno.SysLog)")
public void logPointCut() {}
```

添加环绕通知@Around

```javascript
@Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long beginTime = System.currentTimeMillis();
        Object result = point.proceed();
        long time = System.currentTimeMillis() - beginTime;
        try {
            //实现保存日志逻辑
            saveLog(point, time);
        } catch (Exception e) {
            
        }
        return result;
    }
```

实现保存日志逻辑saveLog

```javascript
private void saveLog(ProceedingJoinPoint joinPoint, long time) {

    // 获取方法的关键信息，类，包
    MethodSignature signature = (MethodSignature) joinPoint.getSignature();
    Method method = signature.getMethod();
    SysLogEntity sysLogEntity = new SysLogEntity();
    sysLogEntity.setExeuTime(time);
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    sysLogEntity.setCreateDate(dateFormat.format(new Date()));
    SysLog sysLog = method.getAnnotation(SysLog.class);
    if(sysLog != null) {
        //注解上的描述
        sysLogEntity.setRemark(sysLog.value());
    }
    //请求的 类名、方法名
    String className = joinPoint.getTarget().getClass().getName();
    String methodName = signature.getName();
    sysLogEntity.setClassName(className);
    sysLogEntity.setMethodName(methodName);
    //请求的参数
    Object[] args = joinPoint.getArgs();
    try {
        List<String> list = new ArrayList<String>();
        for (Object o : args) {
            list.add(o.toString());
        }
        sysLogEntity.setParams(list.toString());
    } catch (Exception e){

    }
    sysLogService.save(sysLogEntity);
}
```

MethodSignature主要实现的是返回值类，方法名和形式参数

## **五、测试**

通过发送get请求：127.0.0.1:8081/aop/test?name=Tom&age=18

同时以debug模式运行项目，打点查看参数

![img](https://ask.qcloudimg.com/http-save/2308184/trrpmnf4fa.png?imageView2/2/w/1620)

可以看到MethodSignature中的参数

以及sysLogEntity赋值的各个参数。

## **六、总结**

1、横切关注点 对哪些方法进行拦截，拦截后怎么处理，这些关注点称之为横切关注点

2、切面（aspect）->（通知+切点） 类是对物体特征的抽象，切面就是对横切关注点的抽象。  通知+切点  意思就是所有要被应用到增强（advice）代码的地方。(包括方法的方位信息)

3、连接点（joinpoint）->（被拦截的方法） 被拦截到的点，因为Spring只支持方法类型的连接点，所以在Spring中连接点指的就是被拦截的方法，实际上连接点还可以是字段或者构造器

4、切入点（pointcut）->（描述拦截那些方法的部分） 对连接点进行拦截的定义

5、通知（advice）->（拦截后执行自己业务逻辑的那些部分） 所谓通知指的就是指拦截到连接点之后要执行的代码，通知分为前置、后置、异常、最终、环绕通知五类  这玩意也叫 增强 

在逻辑层次上包括了我们抽取的公共逻辑和方位信息。因为Spring只能方法级别的应用AOP,也就是我们常见的before,after,after-returning,after-throwing,around五种，意思就是在方法调用前后，异常时候执行我这段公共逻辑呗。



文章来源：[Spring Boot2(六)：使用Spring Boot整合AOP面向切面编程](https://niaobulashi.com/archives/springboot-aop.html)