Docker mysql 把数据存储在本地目录，很简单，只需要映射本地目录到容器即可

加上-v参数

```javascript
$ docker run -d -e MYSQL_ROOT_PASSWORD=admin --name mysql -v /data/mysql/data:/var/lib/mysql -p 3306:3306 mysql 
```

还可以指定配置文件

```javascript
docker run -d -e MYSQL_ROOT_PASSWORD=admin --name mysql -v /data/mysql/my.cnf:/etc/mysql/my.cnf -v /data/mysql/data:/var/lib/mysql -p 3306:3306 mysql 
```

这样，即可修改配置文件，还能把数据存在本地目录，一举两得，-v 参数可以多次使用，每次映射一个目录，通过这种方式，很容易进行配置。。

