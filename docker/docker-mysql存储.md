Docker mysql 把数据存储在本地目录，很简单，只需要映射本地目录到容器即可

加上-v参数

```javascript
$ docker run -d -e MYSQL_ROOT_PASSWORD=qq123 --name mysql -v /root/mysql/main/data:/var/lib/mysql -p 3306:3306 mysql 
```

还可以指定配置文件

```javascript
docker run -d -e MYSQL_ROOT_PASSWORD=admin --name mysql -v /root/mysql/main/my.cnf:/etc/mysql/my.cnf -v /root/mysql/main/data:/var/lib/mysql -p 3306:3306 mysql 
```

这样，即可修改配置文件，还能把数据存在本地目录，一举两得，-v 参数可以多次使用，每次映射一个目录，通过这种方式，很容易进行配置。。

#### redis

1. 拉取redis镜像 docker pull redis:latest
2. 在服务器/home/redis/redis.conf创建redis基本配置文件（复制下面的链接文本内容）[redi基础配置下载地址（官方）](https://links.jianshu.com/go?to=http%3A%2F%2Fdownload.redis.io%2Fredis-stable%2Fredis.conf)
3. 修改以下几处
	 1).  bind 127.0.0.1 #注释掉这部分，这是限制redis只能本地访问
	 2).  protected-mode no # 默认yes，开启保护模式，限制为本地访问
	 3).  daemonize no # 默认no 如果是yes就改为no
	 4).  requirepass root # 这个是可选 需不需要设置密码 我设置的root
4. 创建容器 在服务器执行以下命令



```kotlin
docker run -p 6380:6379 \
--name redis \
-v /home/redis/redis.conf:/etc/redis/redis.conf \
-v /home/redis/data:/data \
-id redis \
--requirepass root \
--appendonly yes
```

-v 是挂在目录 第一个-v是挂在配置文件 第二个-v 是设置redis数据持久化到本地磁盘的地址
 -p 是设置端口
 –name 是设置容器名称
 –appendonly yes 以守护容器启动 相当于可以后台运行

执行完后 docker ps -a 查看容器是否启动
 可以远程连接redis



```css
redis-server [启动window上的redis服务]
redis-cli -h 47.98.xx.xxx -p 6380 -a root
-h 服务器地址
-p 端口号
-a 密码 没有设置可以省略
```

1. 创建sh文件

```bash
#!  /bin/bash
docker stop redis
docker rm redis
docker run --name redis -p 6380:6379 \
-v /home/redis/redis.conf:/etc/redis/redis.conf \
-v /home/redis/data:/data \
-id redis \
--appendonly yes
```

#### mysql

1. 拉去mysql镜像(版本自己选择) `docker pull mysql:5.7`
2. 启用一个简单mysql示例
	 `docker run --name mysql -e MYSQL_ROOT_PASSWORD=root -d mysql:5.7`
3. 在服务器中创建文件夹

```undefined
mkdir -p /home/mysql
```

1. 将MySQL初始数据复制到服务器

```jsx
docker cp mysql:/etc/mysql/conf.d /home/mysql/
docker cp mysql:/var/lib/mysql /home/mysql/
```

1. 在服务器`/home/mysql/conf.d`下创建`my.cnf`文件

> **文件内容**(作用是设置编码格式)

```csharp
[client]
default-character-set=utf8mb4
[mysql]
default-character-set=utf8mb4
[mysqld]
character-set-server=utf8mb4 
collation-server=utf8mb4_general_ci
```

1. 停止并删除MySQL示例

```undefined
docker stop mysql
docker rm mysql
```

- 正式启动

```jsx
docker run --name mysql5.7 \
-v /home/mysql/conf.d:/etc/mysql/conf.d \
-v /home/mysql/mysql:/var/lib/mysql \
-p 3306:3306 \
-e MYSQL_ROOT_PASSWORD=root \
-d mysql:5.7 
```

1. 脚本编写`mysql.sh`

```bash
#!   /bin/bash
docker stop mysql5.7
docker rm mysql5.7
docker run --name mysql5.7 \
    -v /home/mysql/conf.d:/etc/mysql/conf.d \
    -v /home/mysql/mysql:/var/lib/mysql \
    -p 3306:3306 \
    -e MYSQL_ROOT_PASSWORD=root \
    -d mysql:5.7
```
