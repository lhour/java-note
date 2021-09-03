```xml
<dependencies>
    <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>4.13.1</version>
    </dependency>
  </dependencies>
```

在 dependencies 下的依赖子工程都会继承

可是子工程并不一定会用到父工程的依赖

所以可以使用

```xml
<dependencyMangement>
	<dependency>
      <groupId>mysql</groupId>
      <artifactId>mysql-connector-java</artifactId>
      <version>8.0.26</version>
    </dependency>

    <dependency>
      <groupId>com.alibaba</groupId>
      <artifactId>dubbo</artifactId>
      <version>2.6.2</version>
    </dependency>
</dependencyMangement>
```

这两个依赖并不会在子工程中自动引入

需要进行声明

```xml
<dependencies>
    <dependency>
      <groupId>com.alibaba</groupId>
      <artifactId>dubbo</artifactId>
    </dependency>
</dependencies>
```

不需要加版本依赖，会默认使用父工程下的版本

但如果加上，则按照子工程的版本号为准

