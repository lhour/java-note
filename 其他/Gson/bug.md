## 类重复

```java
[INFO] --- maven-compiler-plugin:3.8.1:compile (default-compile) @ gson ---
[INFO] Changes detected - recompiling the module!
[INFO] Compiling 77 source files to F:\github\point\gson-master\gson\target\classes
[INFO] /F:/github/point/gson-master/gson/src/main/java/com/google/gson/JsonArray.java: 某些输入文件使用或覆盖了已过时的 API。
[INFO] /F:/github/point/gson-master/gson/src/main/java/com/google/gson/JsonArray.java: 有关详细信息, 请使用 -Xlint:deprecation 重新编译。
[INFO] -------------------------------------------------------------
[ERROR] COMPILATION ERROR :
[INFO] -------------------------------------------------------------
[ERROR] /F:/github/point/gson-master/gson/target/generated-sources/java-templates/com/google/gson/internal/GsonBuildConfig.java:[25,14] 类重复: com.google.gson.internal.GsonBuildConfig
[INFO] 1 error
[INFO] -------------------------------------------------------------
[INFO] ------------------------------------------------------------------------
[INFO] Reactor Summary for Gson Parent 2.8.9-SNAPSHOT:
[INFO]
[INFO] Gson Parent ........................................ SUCCESS [  0.451 s]
[INFO] Gson ............................................... FAILURE [  4.492 s]
[INFO] ------------------------------------------------------------------------
[INFO] BUILD FAILURE
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  5.088 s
[INFO] Finished at: 2021-09-29T18:23:34+08:00
[INFO] ------------------------------------------------------------------------
[ERROR] Failed to execute goal org.apache.maven.plugins:maven-compiler-plugin:3.8.1:compile (default-compile) on project gson: Compilation failure
[ERROR] /F:/github/point/gson-master/gson/target/generated-sources/java-templates/com/google/gson/internal/GsonBuildConfig.java:[25,14] 类重复: com.google.gson.internal.GsonBuildConfig
[ERROR]
[ERROR] -> [Help 1]
                                                                                 [ERROR]
[ERROR] To see the full stack trace of the errors, re-run Maven with the -e switch.
[ERROR] Re-run Maven using the -X switch to enable full debug logging.
[ERROR]
[ERROR] For more information about the errors and possible solutions, please read the following articles:
[ERROR] [Help 1] http://cwiki.apache.org/confluence/display/MAVEN/MojoFailureException
[ERROR]
[ERROR] After correcting the problems, you can resume the build with the command
[ERROR]   mvn <args> -rf :gson
```



这个文件是自动生成的，上传github时会无视out文件。但这个文件仍在，自己本地构建的时候要删掉



## writing the processed class files to a directory

```
 [proguard] ProGuard, version 6.2.2
 [proguard] Note: you're writing the processed class files to a directory [F:\github\point\gson-master\gson\target\test-classes-obfuscated-outjar].
 [proguard]       This will likely cause problems with obfuscated mixed-case class names.
 [proguard]       You should consider writing the output to a jar file, or otherwise
 [proguard]       specify '-dontusemixedcaseclassnames'.
 [proguard] Reading program directory [F:\github\point\gson-master\gson\target\test-classes-obfuscated-injar] (filtered)
 [proguard] Reading library directory [F:\github\point\gson-master\gson\target\classes] (filtered)
 [proguard] Reading library jmod [C:\Program Files\AdoptOpenJDK\jdk-16.0.1.9-hotspot\jmods\java.base.jmod] (filtered)
 [proguard] Error: Can't read [C:\Program Files\AdoptOpenJDK\jdk-16.0.1.9-hotspot\jmods\java.base.jmod] (Can't process class [module-info.class] (Unsupported version number [60.0] (maximum 57.0, Java 13)))
[INFO] ------------------------------------------------------------------------
[INFO] Reactor Summary for Gson Parent 2.8.9-SNAPSHOT:
[INFO]
[INFO] Gson Parent ........................................ SUCCESS [  0.559 s]
[INFO] Gson ............................................... FAILURE [ 38.281 s]
[INFO] ------------------------------------------------------------------------
[INFO] BUILD FAILURE
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  39.022 s
[INFO] Finished at: 2021-09-29T18:29:25+08:00
[INFO] ------------------------------------------------------------------------
[ERROR] Failed to execute goal com.github.wvengen:proguard-maven-plugin:2.5.1:proguard (default) on project gson: Obfuscation failed (result=1) -> [Help 1]

```

删掉对应的插件