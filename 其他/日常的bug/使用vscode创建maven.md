IDEA 创建 MAVEN 项目卡在 Generating project in Batch mode。是 MAVEN 一直在请求：

http://repo1.maven.org/maven2/archetype-catalog.xml

这个项目目录的 xml 文件，由于墙的问题，加载缓慢，等待时间十分漫长。

解决方法：修改 setting.xml 文件，把 mirror 改成阿里的镜像。

1、打开 maven 目录下的 conf/setting.xml，注意要是 idea 使用的 maven。

2、搜索 <mirrors>；找到 <mirrors>。在 <mirrors> 节点下添加。

```xml
<mirror>
      <id>alimaven</id>
      <name>aliyun maven</name>
      <url>http://maven.aliyun.com/nexus/content/groups/public/</url>
      <mirrorOf>central</mirrorOf>        
</mirror>
```

3、运行 MVN 命令的时候加上 -DarchetypeCatalog=local 这个参数。

或者就一直等，大概也就20分种不到。