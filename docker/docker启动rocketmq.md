```
docker pull rocketmqinc/rocketmq
docker pull styletang/rocketmq-console-ng　
```

### 启动nameserver

```
docker run -d 
-p 9876:9876 
-v {主机路径}/data/namesrv/logs:/root/logs 
-v {主机路径}/data/namesrv/store:/root/store 
--name mqnamesrv 
-e "MAX_POSSIBLE_HEAP=100000000" 
ocketmqinc/rocketmq sh mqnamesrv
```

{主机路径} = /root/rocketmq

#### 在 {主机路径}/conf目录下创建broker.conf

```
brokerClusterName = DefaultCluster
brokerName = broker-a
brokerId = 0
deleteWhen = 04
fileReservedTime = 48
brokerRole = ASYNC_MASTER
flushDiskType = ASYNC_FLUSH
brokerIP1 = {docker宿主机IP}
```

#### 启动broker

```
docker run -d 
-p 10911:10911 
-p 10909:10909 
-v {主机路径}/data/broker/logs:/root/logs 
-v  {主机路径}/rocketmq/data/broker/store:/root/store 
-v  {主机路径}/conf/broker.conf:/opt/rocketmq/conf/broker.conf 
--name rmqbroker 
--link mqnamesrv:namesrv 
-e "NAMESRV_ADDR=namesrv:9876" 
-e "MAX_POSSIBLE_HEAP=200000000" 
rocketmqinc/rocketmq sh mqbroker 
-c /opt/rocketmq/conf/broker.conf
```

#### 启动控制台

```
docker run 
-e "JAVA_OPTS=-Drocketmq.config.namesrvAddr={docker宿主机ip}:9876 -Drocketmq.config.isVIPChannel=false" 
-p 8081:8080 
-t 
styletang/rocketmq-console-ng
```

### 我的

```
docker run -d -p 9876:9876 -v /root/rocketmq/data/namesrv/logs:/root/logs -v /root/rocketmq/data/namesrv/store:/root/store --name mqnamesrv -e "MAX_POSSIBLE_HEAP=100000000" rocketmqinc/rocketmq sh mqnamesrv
```

```
brokerClusterName = DefaultCluster
brokerName = broker-a
brokerId = 0
deleteWhen = 04
fileReservedTime = 48
brokerRole = ASYNC_MASTER
flushDiskType = ASYNC_FLUSH
brokerIP1 = 1.116.106.46
```

```
docker run -d -p 10911:10911 -p 10909:10909 -v  /root/rocketmq/data/broker/logs:/root/logs -v  /root/rocketmq/rocketmq/data/broker/store:/root/store -v  /root/rocketmq/conf/broker.conf:/opt/rocketmq/conf/broker.conf --name rmqbroker --link mqnamesrv:namesrv -e "NAMESRV_ADDR=namesrv:9876" -e "MAX_POSSIBLE_HEAP=200000000" rocketmqinc/rocketmq sh mqbroker -c /opt/rocketmq/conf/broker.conf
```

```
docker run 
-e "JAVA_OPTS=-Drocketmq.config.namesrvAddr=1.116.106.46:9876 -Drocketmq.config.isVIPChannel=false" 
-p 8081:8080 
-t 
--name mqcon
styletang/rocketmq-console-ng
```

