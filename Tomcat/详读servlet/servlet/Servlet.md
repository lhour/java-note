## 什么是Serlvet？
Servlet其实就是一个遵循Servlet开发的java类。
Serlvet是由服务器调用的，运行在服务器端。

## 为什么要用到Serlvet？
我们编写java程序想要在网上实现 聊天、发帖、这样一些的交互功能
普通的java技术是非常难完成的。
sun公司就提供了Serlvet这种技术。

## Servlet 任务
Servlet 执行以下主要任务：

1. 读取客户端（浏览器）发送的显式的数据。这包括网页上的 HTML 表单，或者也可以是来自 applet 或自定义的 HTTP 客户端程序的表单。
2. 读取客户端（浏览器）发送的隐式的 HTTP 请求数据。这包括 cookies、媒体类型和浏览器能理解的压缩格式等等。
3. 处理数据并生成结果。这个过程可能需要访问数据库，执行 RMI 或 CORBA 调用，调用 Web 服务，或者直接计算得出对应的响应。
4. 发送显式的数据（即文档）到客户端（浏览器）。该文档的格式可以是多种多样的，包括文本文件（HTML 或 XML）、二进制文件（GIF 图像）、Excel 等。
5. 发送隐式的 HTTP 响应到客户端（浏览器）。这包括告诉浏览器或其他客户端被返回的文档类型（例如 HTML），设置 cookies 和缓存参数，以及其他类似的任务。

## Serlvet生命周期
Servlet生命周期可分为5个步骤
### 加载Servlet。当Tomcat第一次访问Servlet的时候，Tomcat会负责创建Servlet的实例

### 初始化。当Servlet被实例化后，Tomcat会调用init()方法初始化这个对象

### 处理服务。当浏览器访问Servlet的时候，Servlet 会调用service()方法处理请求

### 销毁。当Tomcat关闭时或者检测到Servlet要从Tomcat删除的时候会自动调用destroy()方法，让该实例释放掉所占的资源。一个Servlet如果长时间不被使用的话，也会被Tomcat自动销毁

### 卸载。当Servlet调用完destroy()方法后，等待垃圾回收。如果有需要再次使用这个Servlet，会重新调用init()方法进行初始化操作。

### 简单总结：
只要访问Servlet，service()就会被调用。
init()只有第一次访问Servlet的时候才会被调用。 
destroy()只有在Tomcat关闭的时候才会被调用。