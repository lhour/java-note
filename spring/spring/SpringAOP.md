## 术语
1. Aspect 切面，增加功能，日志，事务，统计权限等
2. JoinPoint 连接业务和切面的位置
3. Pointcut 切入点，多个连接点方法的集合
4. Advice 通知，通知表示切面执行的时间

## jdk
jdk动态代理必须实现目标类的接口

## CGLIB是一个强大的高性能的代码生成包，
它可以在运行期扩展Java类与实现Java接口。
它广泛的被许多AOP的框架使用，
例如Spring AOP和dynaop，
为他们提供方法的interception（拦截）。

## aop实现
spring中有aop的包，但是比较笨重

## aspectJ：开源动态代理框架
spring继承了aspectJ，通过spring可以使用aspectJ
1. 使用xml
2. 注解
3. 1，切面的执行时间
      1. @Before
      2. @AfterReturning
      3. @Around
      4. @AfterThrowing
      5. @After
   1. 切面执行位置 execution(权限 方法返回值 参数 异常类型)
      1. * 任意多字符
      2. '' 多个参数/当前包及其子包
      3. + 当前类及其字类/接口及其实现类
4. 使用方式
   1. 加入依赖
      1. spring依赖
      2. aspectJ依赖
      3. juit依赖
   2. 创建目标类
   3. 创建切面类
      1. 类上加@Aspect
      2. 方法上加通知注解
   4. spring配置文件
      1. 声明目标对象
      2. 切面类对象
      3. 声明aspect自动代理生成器标签
      4. 
