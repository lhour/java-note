## 整合jsp

1. 添加依赖
2. pom文件中修改资源文件（使用idea可以点击操作）
3. 配置文件填写
4. 注意方法返回时设置name

```xml
	<!-- 整合JSP -->
		<dependency>
			<groupId>org.apache.tomcat.embed</groupId>
			<artifactId>tomcat-embed-jasper</artifactId>
		</dependency>
```

这个资源的添加不一定满足其他情况，后续可能会发生更改

```xml
	<resources>
			<resource>
                <!-- 资源文件 -->
				<directory>src/main/webapp</directory>
                <!-- 输出位置 -->
				<targetPath>META-INF/resources</targetPath>
                <!-- 编译那些文件 -->
				<includes>
					<include>*.*</include>
				</includes>
			</resource>
			<resource>
				<directory>src/main/resources</directory>
			</resource>
		</resources>
```

```properties
spring.mvc.view.prefix=/
spring.mvc.view.suffix=.jsp
```

```java
	@RequestMapping(value = "/myjsp")
    public ModelAndView myjsp(){
        ModelAndView mv = new ModelAndView();
        mv.addObject("name", school.getName());
        mv.setViewName("myjsp");
        return mv;
    }
```

viewName和文件路径一致即可

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>

    </head>
    <body>
        <h1>${name}</h1>
    </body>
</html>
```

## 整合mybatis

依赖

```xml
	<!-- 整合mybatis -->
		<dependency>
			<groupId>mysql</groupId>
			<artifactId>mysql-connector-java</artifactId>
		</dependency>

		<dependency>
			<groupId>org.mybatis.spring.boot</groupId>
			<artifactId>mybatis-spring-boot-starter</artifactId>
			<version>2.2.0</version>
		</dependency>
```

自动生成的插件

```xml
			<plugin>
				<groupId>org.mybatis.generator</groupId>
				<artifactId>mybatis-generator-maven-plugin</artifactId>
				<version>1.4.0</version>
				<configuration>
					<!-- 配置文件地址 -->
					<configurationFile>generatorConfig.xml</configurationFile>
					<verbose>true</verbose>
					<overwrite>true</overwrite>
				</configuration>
			</plugin>
```

配置文件

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE generatorConfiguration
        PUBLIC "-//mybatis.org//DTD MyBatis Generator Configuration 1.0//EN"
        "http://mybatis.org/dtd/mybatis-generator-config_1_0.dtd">

<generatorConfiguration>
	<!-- 驱动路径 -->
    <classPathEntry location="C:\Users\86178\.m2\repository\mysql\mysql-connector-java\8.0.26\mysql-connector-java-8.0.26.jar"/>
    <!-- context 是逆向工程的主要配置信息 -->
    <!-- id：起个名字 -->
    <!-- targetRuntime：设置生成的文件适用于那个 mybatis 版本 -->
    <context id="default" targetRuntime="MyBatis3">


<!--        1111111111-->
        <!--optional,指在创建class时，对注释进行控制-->
        <commentGenerator>
            <!-- 是否去除自动生成日期的注释 true：是 ： false:否 -->
            <property name="suppressDate" value="true"/>
            <!-- 是否去除自动生成的注释 true：是 ： false:否 -->
            <property name="suppressAllComments" value="true"/>
        </commentGenerator>

<!--        2222222222-->
        <!--jdbc的数据库连接 wg_insert 为数据库名字-->
        <jdbcConnection driverClass="com.mysql.cj.jdbc.Driver"
                        connectionURL="jdbc:mysql://192.168.163.128:3306/springboot?useUnicode=true&amp;characeterEncoding=utf-8&amp;serverTimezone=UTC"
                        userId="root"
                        password="admin">
            <!--解决连接到别的数据库表问题-->
            <property name="nullCatalogMeansCurrent" value="true"/>
            <!--解决不生成delete、update等-->
            <property name="useInformationSchema" value="true"/>
        </jdbcConnection>

<!--        333333333-->
        <!--非必须，类型处理器，在数据库类型和java类型之间的转换控制-->
        <javaTypeResolver>
            <!-- 默认情况下数据库中的 decimal，bigInt 在 Java 对应是 sql 下的 BigDecimal 类 -->
            <!-- 不是 double 和 long 类型 -->
            <!-- 使用常用的基本类型代替 sql 包下的引用类型 -->
            <property name="forceBigDecimals" value="false"/>
        </javaTypeResolver>

<!--        444444444-->
        <!-- targetPackage：生成的实体类所在的包 -->
        <!-- targetProject：生成的实体类所在的硬盘位置 -->
        <javaModelGenerator targetPackage="com.example.demo.model"
                            targetProject="src/main/java">
            <!-- 是否允许子包 -->
            <property name="enableSubPackages" value="false"/>
            <!-- 是否对modal添加构造函数 -->
            <property name="constructorBased" value="true"/>
            <!-- 是否清理从数据库中查询出的字符串左右两边的空白字符 -->
            <property name="trimStrings" value="true"/>
            <!-- 建立modal对象是否不可改变 即生成的modal对象不会有setter方法，只有构造方法 -->
            <property name="immutable" value="false"/>
        </javaModelGenerator>

<!--        555555555-->
        <!-- targetPackage 和 targetProject：生成的 mapper 文件的包和位置 -->
        <sqlMapGenerator targetPackage="mapper"
                            targetProject="src/main/resources">
            <!-- 针对数据库的一个配置，是否把 schema 作为字包名 -->
            <property name="enableSubPackages" value="false"/>
        </sqlMapGenerator>

<!--        6666666666-->
        <!-- targetPackage 和 targetProject：生成的 interface 文件的包和位置 -->
        <javaClientGenerator type="XMLMAPPER"
                            targetPackage="com.example.demo.dao" targetProject="src/main/java">
            <!-- 针对 oracle 数据库的一个配置，是否把 schema 作为字包名 -->
            <property name="enableSubPackages" value="false"/>
        </javaClientGenerator>

<!--        7777777777-->
        <!-- tableName是数据库中的表名，domainObjectName是生成的JAVA实体名，后面的参数不用改，要生成更多的表就在下面继续加table标签 -->
        <table tableName="student" domainObjectName="Student"
                enableCountByExample="false" enableUpdateByExample="false"
                enableDeleteByExample="false" enableSelectByExample="false"
                selectByExampleQueryId="false"></table>
        <!-- tableName是数据库中的表名，domainObjectName是生成的JAVA模型名，后面的参数不用改，要生成更多的表就在下面继续加table标签 -->
        <table tableName="course" domainObjectName="Course"
                enableCountByExample="false" enableUpdateByExample="false"
                enableDeleteByExample="false" enableSelectByExample="false"
                selectByExampleQueryId="false"></table>
    </context>
</generatorConfiguration>
```

