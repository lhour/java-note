## "The Language Support for Java server crashed" 问题解决方案

```
环境：Windows
vs code 1.35
6.14日最新发布的VSCodeJavaInstaller-online-win-0.1.0.exe
RedHat Server 报错
```

### 错误是在vs code启动后立即显示的，原文如下

```java
The Language Support for Java server crashed 5 times in the last 3 minutes. The server will not be restarted
```
### 解决

```java
找到"java.jdt.ls.vmargs" 项
重置键值为 "-noverify -Xmx1G -XX:+UseG1GC -XX:+UseStringDeduplication"
```

### 原因

```java
问题原因
vs code插件卸载残留问题
曾经安装过并现已卸载的插件，将lombook依赖加入了路径，卸载时没有清除
路径找不到文件而导致server崩溃
```
