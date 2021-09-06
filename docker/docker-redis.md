redis在官网下载 redis.conf 配置文件，可以放在任何位置

列出正在运行的容器的更多详细信息，例如IP地址,命令格式如下：

```c
docker inspect <friendly-name|container-id>
```

通过docker logs命令可以查看容器的日志,命令格式如下：

```text
docker logs <friendly-name|container-id>
```

假设我们要部署多个redis不可能每次都自己亲手来找一个端口来映射，docker是支持自动绑定到可用端口上的，只要用`-p 6379`这个参数就可以了，像下面这样。

```text
$ docker run -d --name redisDynamic -p 6379 redis:latest
dcd3dabe51c3f0a8ddbdfd
```

到底绑定了哪个端口，可以用以下命令查询（这个时候就用到自定义的名称redisDynamic了），当然直接用`docker ps`也是可以的。

```text
$ docker port redisDynamic 6379
0.0.0.0:32768
```

我们查询到官网上的redis镜像存储数据的目录在`/data`目录,然后我们存储在本地`/opt/docker/data/redis`
使用参数`-v 本机位置:容器内位置`

```text
docker run -d --name redisMapped -v /opt/docker/data/redis:/data redis
c9c154695500260407d99d
```

访问正在运行的docker
`docker attach <container>` 要attach上去的容器必须正在运行。
`docker exec -it <container> bash|sh`直接访问容器内的bash shell。

```text
$ docker attach c9c154695500 /bin/bash
root@c9c154695500:/data# exit
$ docker exec -it c9c154695500 /bin/bash
root@c9c154695500:/data# exit
```
