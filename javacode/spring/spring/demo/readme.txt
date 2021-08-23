出现问题
1.
```java
八月 20, 2021 3:01:43 下午 org.springframework.context.support.AbstractApplicationContext refresh
警告: Exception encountered during context initialization - cancelling refresh attempt: 
org.springframework.beans.factory.BeanCreationException: 
Error creating bean with name 'myStudent1' defined in file 
[D:\文件\学习笔记\javacode\spring\spring\demo\target\classes\com\example\learn1\Student.class]: 
Initialization of bean failed; nested exception is java.lang.IllegalArgumentException: 
warning no match for this type name: com.example.aspect1 [Xlint:invalidAbsoluteTypeName]
```

表达式出错了 漏写[SomeServiceImp]
```java
    @Before(value = "execution(public void com.example.aspect1.[SomeServiceImp].doSome(String, Integer)) .")
    public void MyBefore() {
        System.out.println("【前置通知】切面功能：在目标方法之前输出 ：" + new Date());
    }
```

2.
spring对象创建就是执行构造方法，即使没有getBean，也会执行
3.
%TESTC  1 v2
%TSTTREE2,com.example.test4,true,1,false,1,test4,,[engine:junit-jupiter]/[class:com.example.test4]
%TSTTREE3,test(com.example.test4),false,1,false,2,test(),,[engine:junit-jupiter]/[class:com.example.test4]/[method:test()]
%TESTS  3,test(com.example.test4)
%ERROR  3,test(com.example.test4)
%TRACES 
org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'sqlSessionFactory' defined in class path resource [applicationContext.xml]: Invocation of init method failed; nested exception is org.springframework.core.NestedIOException: Failed to parse config resource: class path resource [mybatis.xml]; nested exception is org.apache.ibatis.builder.BuilderException: Error parsing SQL Mapper Configuration. Cause: org.apache.ibatis.builder.BuilderException: Error resolving class. Cause: org.apache.ibatis.type.TypeException: Could not resolve type alias ''.  Cause: java.lang.ClassNotFoundException: Cannot find class: 
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.initializeBean(AbstractAutowireCapableBeanFactory.java:1796)
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean(AbstractAutowireCapableBeanFactory.java:595)
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean(AbstractAutowireCapableBeanFactory.java:517)
        at org.springframework.beans.factory.support.AbstractBeanFactory.lambda$doGetBean$0(AbstractBeanFactory.java:323)
        at org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.getSingleton(DefaultSingletonBeanRegistry.java:222)
        at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:321)
        at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:202)
        at org.springframework.beans.factory.support.DefaultListableBeanFactory.preInstantiateSingletons(DefaultListableBeanFactory.java:863)
        at org.springframework.context.support.AbstractApplicationContext.finishBeanFactoryInitialization(AbstractApplicationContext.java:878)
        at org.springframework.context.support.AbstractApplicationContext.refresh(AbstractApplicationContext.java:550)
        at org.springframework.context.support.ClassPathXmlApplicationContext.<init>(ClassPathXmlApplicationContext.java:144)
        at org.springframework.context.support.ClassPathXmlApplicationContext.<init>(ClassPathXmlApplicationContext.java:85)
        at com.example.test4.test(test4.java:12)
        at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
        at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
        at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
        at java.lang.reflect.Method.invoke(Method.java:498)
        at org.junit.platform.commons.util.ReflectionUtils.invokeMethod(ReflectionUtils.java:686)
        at org.junit.jupiter.engine.execution.MethodInvocation.proceed(MethodInvocation.java:60)
        at org.junit.jupiter.engine.execution.InvocationInterceptorChain$ValidatingInvocation.proceed(InvocationInterceptorChain.java:131)
        at org.junit.jupiter.engine.extension.TimeoutExtension.intercept(TimeoutExtension.java:149)
        at org.junit.jupiter.engine.extension.TimeoutExtension.interceptTestableMethod(TimeoutExtension.java:140)
        at org.junit.jupiter.engine.extension.TimeoutExtension.interceptTestMethod(TimeoutExtension.java:84)
        at org.junit.jupiter.engine.execution.ExecutableInvoker$ReflectiveInterceptorCall.lambda$ofVoidMethod$0(ExecutableInvoker.java:115)
        at org.junit.jupiter.engine.execution.ExecutableInvoker.lambda$invoke$0(ExecutableInvoker.java:105)
        at org.junit.jupiter.engine.execution.InvocationInterceptorChain$InterceptedInvocation.proceed(InvocationInterceptorChain.java:106)
        at org.junit.jupiter.engine.execution.InvocationInterceptorChain.proceed(InvocationInterceptorChain.java:64)
        at org.junit.jupiter.engine.execution.InvocationInterceptorChain.chainAndInvoke(InvocationInterceptorChain.java:45)
        at org.junit.jupiter.engine.execution.InvocationInterceptorChain.invoke(InvocationInterceptorChain.java:37)
        at org.junit.jupiter.engine.execution.ExecutableInvoker.invoke(ExecutableInvoker.java:104)
        at org.junit.jupiter.engine.execution.ExecutableInvoker.invoke(ExecutableInvoker.java:98)
        at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.lambda$invokeTestMethod$6(TestMethodTestDescriptor.java:205)
        at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
        at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.invokeTestMethod(TestMethodTestDescriptor.java:201)
        at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.execute(TestMethodTestDescriptor.java:137)
        at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.execute(TestMethodTestDescriptor.java:71)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$5(NodeTestTask.java:135)
        at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$7(NodeTestTask.java:125)
        at org.junit.platform.engine.support.hierarchical.Node.around(Node.java:135)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$8(NodeTestTask.java:123)
        at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.executeRecursively(NodeTestTask.java:122)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.execute(NodeTestTask.java:80)
        at java.util.ArrayList.forEach(ArrayList.java:1259)
        at org.junit.platform.engine.support.hierarchical.SameThreadHierarchicalTestExecutorService.invokeAll(SameThreadHierarchicalTestExecutorService.java:38)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$5(NodeTestTask.java:139)
        at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$7(NodeTestTask.java:125)
        at org.junit.platform.engine.support.hierarchical.Node.around(Node.java:135)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$8(NodeTestTask.java:123)
        at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.executeRecursively(NodeTestTask.java:122)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.execute(NodeTestTask.java:80)
        at java.util.ArrayList.forEach(ArrayList.java:1259)
        at org.junit.platform.engine.support.hierarchical.SameThreadHierarchicalTestExecutorService.invokeAll(SameThreadHierarchicalTestExecutorService.java:38)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$5(NodeTestTask.java:139)
        at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$7(NodeTestTask.java:125)
        at org.junit.platform.engine.support.hierarchical.Node.around(Node.java:135)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$8(NodeTestTask.java:123)
        at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.executeRecursively(NodeTestTask.java:122)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.execute(NodeTestTask.java:80)
        at org.junit.platform.engine.support.hierarchical.SameThreadHierarchicalTestExecutorService.submit(SameThreadHierarchicalTestExecutorService.java:32)
        at org.junit.platform.engine.support.hierarchical.HierarchicalTestExecutor.execute(HierarchicalTestExecutor.java:57)
        at org.junit.platform.engine.support.hierarchical.HierarchicalTestEngine.execute(HierarchicalTestEngine.java:51)
        at org.junit.platform.launcher.core.DefaultLauncher.execute(DefaultLauncher.java:248)
        at org.junit.platform.launcher.core.DefaultLauncher.lambda$execute$5(DefaultLauncher.java:211)
        at org.junit.platform.launcher.core.DefaultLauncher.withInterceptedStreams(DefaultLauncher.java:226)
        at org.junit.platform.launcher.core.DefaultLauncher.execute(DefaultLauncher.java:199)
        at org.junit.platform.launcher.core.DefaultLauncher.execute(DefaultLauncher.java:141)
        at org.eclipse.jdt.internal.junit5.runner.JUnit5TestReference.run(JUnit5TestReference.java:98)
        at org.eclipse.jdt.internal.junit.runner.TestExecution.run(TestExecution.java:40)
        at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.runTests(RemoteTestRunner.java:529)
        at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.runTests(RemoteTestRunner.java:756)
        at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.run(RemoteTestRunner.java:452)
        at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.main(RemoteTestRunner.java:210)
Caused by: org.springframework.core.NestedIOException: Failed to parse config resource: class path resource [mybatis.xml]; nested exception is org.apache.ibatis.builder.BuilderException: Error parsing SQL Mapper Configuration. Cause: org.apache.ibatis.builder.BuilderException: Error resolving class. Cause: org.apache.ibatis.type.TypeException: Could not resolve type alias ''.  Cause: java.lang.ClassNotFoundException: Cannot find class: 
        at org.mybatis.spring.SqlSessionFactoryBean.buildSqlSessionFactory(SqlSessionFactoryBean.java:572)
        at org.mybatis.spring.SqlSessionFactoryBean.afterPropertiesSet(SqlSessionFactoryBean.java:475)
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.invokeInitMethods(AbstractAutowireCapableBeanFactory.java:1855)
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.initializeBean(AbstractAutowireCapableBeanFactory.java:1792)
        ... 77 more
Caused by: org.apache.ibatis.builder.BuilderException: Error parsing SQL Mapper Configuration. Cause: org.apache.ibatis.builder.BuilderException: Error resolving class. Cause: org.apache.ibatis.type.TypeException: Could not resolve type alias ''.  Cause: java.lang.ClassNotFoundException: Cannot find class: 
        at org.apache.ibatis.builder.xml.XMLConfigBuilder.parseConfiguration(XMLConfigBuilder.java:122)
        at org.apache.ibatis.builder.xml.XMLConfigBuilder.parse(XMLConfigBuilder.java:99)
        at org.mybatis.spring.SqlSessionFactoryBean.buildSqlSessionFactory(SqlSessionFactoryBean.java:569)
        ... 80 more
Caused by: org.apache.ibatis.builder.BuilderException: Error resolving class. Cause: org.apache.ibatis.type.TypeException: Could not resolve type alias ''.  Cause: java.lang.ClassNotFoundException: Cannot find class: 
        at org.apache.ibatis.builder.BaseBuilder.resolveClass(BaseBuilder.java:118)
        at org.apache.ibatis.builder.xml.XMLConfigBuilder.databaseIdProviderElement(XMLConfigBuilder.java:305)
        at org.apache.ibatis.builder.xml.XMLConfigBuilder.parseConfiguration(XMLConfigBuilder.java:118)
        ... 82 more
Caused by: org.apache.ibatis.type.TypeException: Could not resolve type alias ''.  Cause: java.lang.ClassNotFoundException: Cannot find class: 
        at org.apache.ibatis.type.TypeAliasRegistry.resolveAlias(TypeAliasRegistry.java:120)
        at org.apache.ibatis.builder.BaseBuilder.resolveAlias(BaseBuilder.java:149)
        at org.apache.ibatis.builder.BaseBuilder.resolveClass(BaseBuilder.java:116)
        ... 84 more
Caused by: java.lang.ClassNotFoundException: Cannot find class: 
        at org.apache.ibatis.io.ClassLoaderWrapper.classForName(ClassLoaderWrapper.java:196)
        at org.apache.ibatis.io.ClassLoaderWrapper.classForName(ClassLoaderWrapper.java:89)
        at org.apache.ibatis.io.Resources.classForName(Resources.java:261)
        at org.apache.ibatis.type.TypeAliasRegistry.resolveAlias(TypeAliasRegistry.java:116)
        ... 86 more
%TRACEE 
%TESTE  3,test(com.example.test4)
%RUNTIME1295

原因时mybatis文件中多了一行，注释掉就好了


4.
%TESTC  1 v2
%TSTTREE2,com.example.test4,true,1,false,1,test4,,[engine:junit-jupiter]/[class:com.example.test4]
%TSTTREE3,test(com.example.test4),false,1,false,2,test(),,[engine:junit-jupiter]/[class:com.example.test4]/[method:test()]
%TESTS  3,test(com.example.test4)
%ERROR  3,test(com.example.test4)
%TRACES 
org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'studentDao' defined in file [D:\文件\学习笔记\javacode\spring\spring\demo\target\classes\com\example\mybatis\dao\StudentDao.class]: Invocation of init method failed; nested exception is java.lang.IllegalArgumentException: org.apache.ibatis.builder.BuilderException: Error creating document instance.  Cause: org.xml.sax.SAXParseException; lineNumber: 20; columnNumber: 29; XML 文档结构必须从头至尾包含在同一个实体内。
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.initializeBean(AbstractAutowireCapableBeanFactory.java:1796)
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean(AbstractAutowireCapableBeanFactory.java:595)
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean(AbstractAutowireCapableBeanFactory.java:517)
        at org.springframework.beans.factory.support.AbstractBeanFactory.lambda$doGetBean$0(AbstractBeanFactory.java:323)
        at org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.getSingleton(DefaultSingletonBeanRegistry.java:222)
        at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:321)
        at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:202)
        at org.springframework.beans.factory.support.DefaultListableBeanFactory.preInstantiateSingletons(DefaultListableBeanFactory.java:863)
        at org.springframework.context.support.AbstractApplicationContext.finishBeanFactoryInitialization(AbstractApplicationContext.java:878)
        at org.springframework.context.support.AbstractApplicationContext.refresh(AbstractApplicationContext.java:550)
        at org.springframework.context.support.ClassPathXmlApplicationContext.<init>(ClassPathXmlApplicationContext.java:144)
        at org.springframework.context.support.ClassPathXmlApplicationContext.<init>(ClassPathXmlApplicationContext.java:85)
        at com.example.test4.test(test4.java:12)
        at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
        at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
        at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
        at java.lang.reflect.Method.invoke(Method.java:498)
        at org.junit.platform.commons.util.ReflectionUtils.invokeMethod(ReflectionUtils.java:686)
        at org.junit.jupiter.engine.execution.MethodInvocation.proceed(MethodInvocation.java:60)
        at org.junit.jupiter.engine.execution.InvocationInterceptorChain$ValidatingInvocation.proceed(InvocationInterceptorChain.java:131)
        at org.junit.jupiter.engine.extension.TimeoutExtension.intercept(TimeoutExtension.java:149)
        at org.junit.jupiter.engine.extension.TimeoutExtension.interceptTestableMethod(TimeoutExtension.java:140)
        at org.junit.jupiter.engine.extension.TimeoutExtension.interceptTestMethod(TimeoutExtension.java:84)
        at org.junit.jupiter.engine.execution.ExecutableInvoker$ReflectiveInterceptorCall.lambda$ofVoidMethod$0(ExecutableInvoker.java:115)
        at org.junit.jupiter.engine.execution.ExecutableInvoker.lambda$invoke$0(ExecutableInvoker.java:105)
        at org.junit.jupiter.engine.execution.InvocationInterceptorChain$InterceptedInvocation.proceed(InvocationInterceptorChain.java:106)
        at org.junit.jupiter.engine.execution.InvocationInterceptorChain.proceed(InvocationInterceptorChain.java:64)
        at org.junit.jupiter.engine.execution.InvocationInterceptorChain.chainAndInvoke(InvocationInterceptorChain.java:45)
        at org.junit.jupiter.engine.execution.InvocationInterceptorChain.invoke(InvocationInterceptorChain.java:37)
        at org.junit.jupiter.engine.execution.ExecutableInvoker.invoke(ExecutableInvoker.java:104)
        at org.junit.jupiter.engine.execution.ExecutableInvoker.invoke(ExecutableInvoker.java:98)
        at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.lambda$invokeTestMethod$6(TestMethodTestDescriptor.java:205)
        at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
        at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.invokeTestMethod(TestMethodTestDescriptor.java:201)
        at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.execute(TestMethodTestDescriptor.java:137)
        at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.execute(TestMethodTestDescriptor.java:71)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$5(NodeTestTask.java:135)
        at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$7(NodeTestTask.java:125)
        at org.junit.platform.engine.support.hierarchical.Node.around(Node.java:135)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$8(NodeTestTask.java:123)
        at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.executeRecursively(NodeTestTask.java:122)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.execute(NodeTestTask.java:80)
        at java.util.ArrayList.forEach(ArrayList.java:1259)
        at org.junit.platform.engine.support.hierarchical.SameThreadHierarchicalTestExecutorService.invokeAll(SameThreadHierarchicalTestExecutorService.java:38)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$5(NodeTestTask.java:139)
        at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$7(NodeTestTask.java:125)
        at org.junit.platform.engine.support.hierarchical.Node.around(Node.java:135)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$8(NodeTestTask.java:123)
        at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.executeRecursively(NodeTestTask.java:122)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.execute(NodeTestTask.java:80)
        at java.util.ArrayList.forEach(ArrayList.java:1259)
        at org.junit.platform.engine.support.hierarchical.SameThreadHierarchicalTestExecutorService.invokeAll(SameThreadHierarchicalTestExecutorService.java:38)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$5(NodeTestTask.java:139)
        at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$7(NodeTestTask.java:125)
        at org.junit.platform.engine.support.hierarchical.Node.around(Node.java:135)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$8(NodeTestTask.java:123)
        at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.executeRecursively(NodeTestTask.java:122)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.execute(NodeTestTask.java:80)
        at org.junit.platform.engine.support.hierarchical.SameThreadHierarchicalTestExecutorService.submit(SameThreadHierarchicalTestExecutorService.java:32)
        at org.junit.platform.engine.support.hierarchical.HierarchicalTestExecutor.execute(HierarchicalTestExecutor.java:57)
        at org.junit.platform.engine.support.hierarchical.HierarchicalTestEngine.execute(HierarchicalTestEngine.java:51)
        at org.junit.platform.launcher.core.DefaultLauncher.execute(DefaultLauncher.java:248)
        at org.junit.platform.launcher.core.DefaultLauncher.lambda$execute$5(DefaultLauncher.java:211)
        at org.junit.platform.launcher.core.DefaultLauncher.withInterceptedStreams(DefaultLauncher.java:226)
        at org.junit.platform.launcher.core.DefaultLauncher.execute(DefaultLauncher.java:199)
        at org.junit.platform.launcher.core.DefaultLauncher.execute(DefaultLauncher.java:141)
        at org.eclipse.jdt.internal.junit5.runner.JUnit5TestReference.run(JUnit5TestReference.java:98)
        at org.eclipse.jdt.internal.junit.runner.TestExecution.run(TestExecution.java:40)
        at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.runTests(RemoteTestRunner.java:529)
        at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.runTests(RemoteTestRunner.java:756)
        at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.run(RemoteTestRunner.java:452)
        at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.main(RemoteTestRunner.java:210)
Caused by: java.lang.IllegalArgumentException: org.apache.ibatis.builder.BuilderException: Error creating document instance.  Cause: org.xml.sax.SAXParseException; lineNumber: 20; columnNumber: 29; XML 文档结构必须从头至尾包含在同一个实体内。
        at org.mybatis.spring.mapper.MapperFactoryBean.checkDaoConfig(MapperFactoryBean.java:83)
        at org.springframework.dao.support.DaoSupport.afterPropertiesSet(DaoSupport.java:44)
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.invokeInitMethods(AbstractAutowireCapableBeanFactory.java:1855)
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.initializeBean(AbstractAutowireCapableBeanFactory.java:1792)
        ... 77 more
Caused by: org.apache.ibatis.builder.BuilderException: Error creating document instance.  Cause: org.xml.sax.SAXParseException; lineNumber: 20; columnNumber: 29; XML 文档结构必须从头至尾包含在同一个实体内。
        at org.apache.ibatis.parsing.XPathParser.createDocument(XPathParser.java:263)
        at org.apache.ibatis.parsing.XPathParser.<init>(XPathParser.java:127)
        at org.apache.ibatis.builder.xml.XMLMapperBuilder.<init>(XMLMapperBuilder.java:81)
        at org.apache.ibatis.builder.xml.XMLMapperBuilder.<init>(XMLMapperBuilder.java:76)
        at org.apache.ibatis.builder.annotation.MapperAnnotationBuilder.loadXmlResource(MapperAnnotationBuilder.java:178)
        at org.apache.ibatis.builder.annotation.MapperAnnotationBuilder.parse(MapperAnnotationBuilder.java:118)
        at org.apache.ibatis.binding.MapperRegistry.addMapper(MapperRegistry.java:72)
        at org.apache.ibatis.session.Configuration.addMapper(Configuration.java:841)
        at org.mybatis.spring.mapper.MapperFactoryBean.checkDaoConfig(MapperFactoryBean.java:80)
        ... 80 more
Caused by: org.xml.sax.SAXParseException; lineNumber: 20; columnNumber: 29; XML 文档结构必须从头至尾包含在同一个实体内。
        at com.sun.org.apache.xerces.internal.util.ErrorHandlerWrapper.createSAXParseException(ErrorHandlerWrapper.java:203)
        at com.sun.org.apache.xerces.internal.util.ErrorHandlerWrapper.fatalError(ErrorHandlerWrapper.java:177)
        at com.sun.org.apache.xerces.internal.impl.XMLErrorReporter.reportError(XMLErrorReporter.java:400)
        at com.sun.org.apache.xerces.internal.impl.XMLErrorReporter.reportError(XMLErrorReporter.java:327)
        at com.sun.org.apache.xerces.internal.impl.XMLScanner.reportFatalError(XMLScanner.java:1473)
        at com.sun.org.apache.xerces.internal.impl.XMLDocumentFragmentScannerImpl.endEntity(XMLDocumentFragmentScannerImpl.java:899)
        at com.sun.org.apache.xerces.internal.impl.XMLDocumentScannerImpl.endEntity(XMLDocumentScannerImpl.java:559)
        at com.sun.org.apache.xerces.internal.impl.XMLEntityManager.endEntity(XMLEntityManager.java:1410)
        at com.sun.org.apache.xerces.internal.impl.XMLEntityScanner.load(XMLEntityScanner.java:1916)
        at com.sun.org.apache.xerces.internal.impl.XMLEntityScanner.peekChar(XMLEntityScanner.java:508)
        at com.sun.org.apache.xerces.internal.impl.XMLDocumentFragmentScannerImpl$FragmentContentDriver.next(XMLDocumentFragmentScannerImpl.java:2716)
        at com.sun.org.apache.xerces.internal.impl.XMLDocumentScannerImpl.next(XMLDocumentScannerImpl.java:602)
        at com.sun.org.apache.xerces.internal.impl.XMLDocumentFragmentScannerImpl.scanDocument(XMLDocumentFragmentScannerImpl.java:505)
        at com.sun.org.apache.xerces.internal.parsers.XML11Configuration.parse(XML11Configuration.java:842)
        at com.sun.org.apache.xerces.internal.parsers.XML11Configuration.parse(XML11Configuration.java:771)
        at com.sun.org.apache.xerces.internal.parsers.XMLParser.parse(XMLParser.java:141)
        at com.sun.org.apache.xerces.internal.parsers.DOMParser.parse(DOMParser.java:243)
        at com.sun.org.apache.xerces.internal.jaxp.DocumentBuilderImpl.parse(DocumentBuilderImpl.java:339)
        at org.apache.ibatis.parsing.XPathParser.createDocument(XPathParser.java:261)
        ... 88 more
%TRACEE 
%TESTE  3,test(com.example.test4)
%RUNTIME1457

5.
库名不能是中文，在配置文件中时，会导致直接无法识别表名
