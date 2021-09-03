```xml
<?xml version="1.0" encoding="UTF-8"?>

<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>

  <groupId>com.hour</groupId>
  <artifactId>demo</artifactId>
  <version>1.0.0</version>
  
   <!--
		packaging标签是指定打包的方式
		默认为jar
	-->
    <packaging>pom</packaging>
    <!--
		pom是项目对象模型(Project Object Module)
		该文件是可以被子工程继承的
	-->
    
    <!--
		maven父工程必须尊守以下两点要求：
		1.packing标签的文本内容必须设置为pom
		2.把src删掉
	-->
    
    
</project>
```

