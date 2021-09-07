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

文件名和上述viewName重名

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

