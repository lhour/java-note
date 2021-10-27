### 镜像选择

```
[root@VM-4-15-centos /]# docker search hadoop
sequenceiq/hadoop-docker         An easy way to try Hadoop 
```

### 端口设置

直接 -P 自动映射 外加 -p x:9000 打开hdfs端口

```
docker run -itd --name hadoop-2 -P -p 9001:9000 uhopper/hadoop
```

查看端口信息

```
[root@VM-4-15-centos /]# docker port hadoop-1
9000/tcp -> 0.0.0.0:9000
9000/tcp -> :::9000
19888/tcp -> 0.0.0.0:49204
19888/tcp -> :::49204
50010/tcp -> 0.0.0.0:49202
50010/tcp -> :::49202
8033/tcp -> 0.0.0.0:49208
8033/tcp -> :::49208
8040/tcp -> 0.0.0.0:49207
8040/tcp -> :::49207
8088/tcp -> 0.0.0.0:49205
8088/tcp -> :::49205
8032/tcp -> 0.0.0.0:49209
8032/tcp -> :::49209
2122/tcp -> 0.0.0.0:49212
2122/tcp -> :::49212
50070/tcp -> 0.0.0.0:49200
50070/tcp -> :::49200
50075/tcp -> 0.0.0.0:49199
50075/tcp -> :::49199
8030/tcp -> 0.0.0.0:49211
8030/tcp -> :::49211
8031/tcp -> 0.0.0.0:49210
8031/tcp -> :::49210
50090/tcp -> 0.0.0.0:49198
50090/tcp -> :::49198
8042/tcp -> 0.0.0.0:49206
8042/tcp -> :::49206
49707/tcp -> 0.0.0.0:49203
49707/tcp -> :::49203
50020/tcp -> 0.0.0.0:49201
50020/tcp -> :::49201

```



### 内部配置

```
docker exec -it hadoop-1 b
```

进入 /etc/profile 

添加全局变量

```
export HADOOP_HOME=/usr/local/hadoop-2.7.0
export PATH=$PATH:$HADOOP_HOME/bin:$HADOOP_HOME/sbin
```

保存生效

```
bash-4.1# vi profile
bash-4.1# source /profile
```

```
bash-4.1# hadoop version
Hadoop 2.7.0
Subversion https://git-wip-us.apache.org/repos/asf/hadoop.git -r d4c8d4d4d203c934e8074b31289a28724c0842cf
Compiled by jenkins on 2015-04-10T18:40Z
Compiled with protoc 2.5.0
From source with checksum a9e90912c37a35c3195d23951fd18f
This command was run using /usr/local/hadoop-2.7.0/share/hadoop/common/hadoop-common-2.7.0.jar
```

### 启动

```
start-dfs.sh
```

