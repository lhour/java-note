在conf文件夹下找到<mirrors>标签
加入一下代码
```java
    <mirror>
      <id>aliyunmaven</id>
      <mirrorOf>*</mirrorOf>
      <name>阿里云公共仓库</name>
      <url>https://maven.aliyun.com/repository/public</url>
    </mirror>
```
之后使用
mvn install
均为使用阿里云的镜像源
