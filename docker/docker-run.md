```c
docker run [OPTIONS] IMAGE 
```

# OPTIONS说明：

- **-a stdin:** 指定标准输入输出内容类型，可选 STDIN/STDOUT/STDERR 三项；

- **-d:** 后台运行容器，并返回容器ID；

- **-i:** 以交互模式运行容器，通常与 -t 同时使用；

- **-P:** 随机端口映射，容器内部端口**随机**映射到主机的端口

- **-p:** 指定端口映射，格式为：**主机(宿主)端口:容器端口**

- **-t:** 为容器重新分配一个伪输入终端，通常与 -i 同时使用；

- **--name="nginx-lb":** 为容器指定一个名称；

- **--dns 8.8.8.8:** 指定容器使用的DNS服务器，默认和宿主一致；

- **--dns-search example.com:** 指定容器DNS搜索域名，默认和宿主一致；

- **-h "mars":** 指定容器的hostname；

- **-e username="ritchie":** 设置环境变量；

- **--env-file=[]:** 从指定文件读入环境变量；

- **--cpuset="0-2" or --cpuset="0,1,2":** 绑定容器到指定CPU运行；

- **-m :**设置容器使用内存最大值；

- **--net="bridge":** 指定容器的网络连接类型，支持 bridge/host/none/container: 四种类型；

- **--link=[]:** 添加链接到另一个容器；

- **--expose=[]:** 开放一个端口或一组端口；

- **--volume , -v:** 绑定一个卷

  

# 实例：

启动mysql：

```
$ docker run -itd --name mysql-test -p 3306:3306 -e MYSQL_ROOT_PASSWORD=123456 mysql
```

启动redis：

```
$ docker run -itd --name redis-test -p 6379:6379 redis 
```

启动mongodb：

```
docker run -itd --name mongo -p 27017:27017 mongo --auth
```

```
$ docker exec -it mongo mongo admin
# 创建一个名为 admin，密码为 123456 的用户。
>  db.createUser({ user:'admin',pwd:'123456',roles:[ { role:'userAdminAnyDatabase', db: 'admin'},"readWriteAnyDatabase"]});
# 尝试使用上面创建的用户信息进行连接。
> db.auth('admin', '123456')
```



### -m

| 选项                | 描述                                                         |
| ------------------- | ------------------------------------------------------------ |
| -m,–memory          | 内存限制，格式是数字加单位，单位可以为 b,k,m,g。最小为 4M    |
| –memory-swap        | 内存+交换分区大小总限制。格式同上。必须必-m设置的大          |
| –memory-reservation | 内存的软性限制。格式同上                                     |
| –oom-kill-disable   | 是否阻止 OOM killer 杀死容器，默认没设置                     |
| –oom-score-adj      | 容器被 OOM killer 杀死的优先级，范围是[-1000, 1000]，默认为 0 |
| –memory-swappiness  | 用于设置容器的虚拟内存控制行为。值为 0~100 之间的整数        |
| –kernel-memory      | 核心内存限制。格式同上，最小为 4M                            |

