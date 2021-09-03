docker的镜像在启动后，即便关闭也还会有残留

```
docker ps -a
```

```
[root@localhost ~]# docker ps -a
CONTAINER ID   IMAGE     COMMAND                  CREATED       STATUS                     PORTS     NAMES
5a2303259558   tomcat    "catalina.sh run"        5 hours ago   Created                              tomcat
35fd43f5d741   tomcat    "catalina.sh run"        5 hours ago   Exited (143) 5 hours ago             tomcat-main
53edbfa0e901   redis     "docker-entrypoint.s…"   7 hours ago   Exited (0) 2 hours ago               redis-main
816425b45abb   mysql     "docker-entrypoint.s…"   7 hours ago   Exited (0) 2 hours ago               mysql-test
d60347a9c3c0   mongo     "docker-entrypoint.s…"   2 days ago    Exited (0) 13 hours ago              mongo
7c7591283a4e   redis     "docker-entrypoint.s…"   5 days ago    Exited (0) 13 hours ago              redis-test
```

可以使用删除

```
docker rm 【id】
```

