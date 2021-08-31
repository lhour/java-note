
service docker start/stop
启动关闭docker

docker images
查看docker现有容器

docker search tomcat
搜索镜像

docker pull docker.io/tomcat
拉去镜像

docker ps
正在运行的容器

```
[root@localhost ~]# docker pull redis
e1acddbe380c: Pull complete 
a31098369fcc: Pull complete 
4a49b0eba86d: Pull complete 
fddf1399efac: Pull complete 
5c6658b59b72: Pull complete 
0b88638a5b77: Pull complete 
Digest: sha256:66ce9bc742609650afc3de7009658473ed601db4e926a5b16d239303383bacad
Status: Downloaded newer image for redis:latest
docker.io/library/redis:latest
```
```
[root@localhost ~]# docker run -itd --name redis-test -p 6379:6379 redis
7c7591283a4e1ef20a505f2379182fa5404a207a6a5f3c7dd411a036240d16b0
```
使用 vscode redis 插件
连接虚拟机ip:6379成功连接