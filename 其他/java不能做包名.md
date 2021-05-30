### 异常
Prohibited package name

```java
Error: A JNI error has occurred, please check your installation and try again
Exception in thread "main" java.lang.SecurityException: Prohibited package name: java.jdk动态代理.静态代理代码.test
        at java.base/java.lang.ClassLoader.preDefineClass(ClassLoader.java:899)
        at java.base/java.lang.ClassLoader.defineClass(ClassLoader.java:1015)
        at java.base/java.security.SecureClassLoader.defineClass(SecureClassLoader.java:151)
        at java.base/jdk.internal.loader.BuiltinClassLoader.defineClass(BuiltinClassLoader.java:821)
        at java.base/jdk.internal.loader.BuiltinClassLoader.findClassOnClassPathOrNull(BuiltinClassLoader.java:719)
        at java.base/jdk.internal.loader.BuiltinClassLoader.loadClassOrNull(BuiltinClassLoader.java:642)
        at java.base/jdk.internal.loader.BuiltinClassLoader.loadClass(BuiltinClassLoader.java:600)
        at java.base/jdk.internal.loader.ClassLoaders$AppClassLoader.loadClass(ClassLoaders.java:178)
        at java.base/java.lang.ClassLoader.loadClass(ClassLoader.java:522)
        at java.base/java.lang.Class.forName0(Native Method)
        at java.base/java.lang.Class.forName(Class.java:427)
        at java.base/sun.launcher.LauncherHelper.loadMainClass(LauncherHelper.java:760)
        at java.base/sun.launcher.LauncherHelper.checkAndLoadMain(LauncherHelper.java:655)
```

java .lang.ClassLoader.preDefineClass这个类对运行的类名做了检查，如果以“ java ”作为1级包名的话，则会抛出异常 ： Prohibited package name: java ，所以修改包名即可解决异常