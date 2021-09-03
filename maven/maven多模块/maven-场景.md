在一个开发项目中可包含有

- commonModel 提供公共的基础服务，公共类，常量类等

- bussinessModel 业务模块，依赖于common模块

- application 可发布的web应用

- 第三方模块 

  

各种模块还可能分成很多小模块，中间都会有所关联，会使用dubbo进行分布式管理

使用分布式开发一定会用到的maven多模块管理

主模块和子模块

