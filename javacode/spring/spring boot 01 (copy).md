## Spring Boot 特性
1. 能够快速创建基于 Spring 的应用程序
2. 能够直接使用 java main 方法启动内嵌的tomcat服务器运行 Spring Boot程序，不需要部署 war 文件
3. 提供 starter POM 来简化Maven 依赖配置，让 Maven 配置更加简单
4. 自动化配置
5. 提供程序健康检查功能
6. 基本可以不用 xml 文件 

## 配置文件

**application.properties**

```properties
# 设置 Tomcat 端口号
server.port=8081

#多环境下的配置文件
server.servlet.context-path=/dev

# 自定义
school.name=myschool
school.add=weihai
```

多环境配置文件

`开发/测试/准生产/生产`

![image-20210907112520633](spring boot 01 (copy).assets/image-20210907112520633.png)



### 自定义注入

```java
	@Value("${school.name}")
    private String name;

    @RequestMapping(value = "/say")
    @ResponseBody
    public String say() {
        
        return "hello " + name;
    }
```

```java
@Component
@ConfigurationProperties(prefix = "school")
public class School {
    
    private String name;

    private String add;

    public String getName() {
        return name;
    }
    ......
```



### yml结尾配置文件

```yml
server:
 port: 8080
 server:
  context: /
```



### request

```java
	@RequestMapping(value = "/say")
    @ResponseBody
    public String say() {
        
        return "hello " + name;
    }

    @RequestMapping(value = "/map")
    @ResponseBody
    public Map<String, String> map() {
        Map<String, String> map = new HashMap<>();
        map.put("name",name);
        map.put("add",add);

        return map;
    }
```



