```
docker run -itd  -e MODE=standalone -v /root/nacos/nacos1/init.d/custom.properties:/home/nacos/init.d/custom.properties -v /root/nacos/naocs1/logs:/home/nacos/logs - --restart always --name nacos nacos/nacos-server
```

```
docker run -itd \
-e PREFER_HOST_MODE=hostname \
-e MODE=cluster \
-e NACOS_APPLICATION_PORT=8848 \
-e NACOS_SERVERS="192.168.163.21:8848 192.168.163.22:8848 192.168.163.23:8848" \
-e SPRING_DATASOURCE_PLATFORM=mysql \
-e MYSQL_SERVICE_HOST=1.116.106.46 \
-e MYSQL_SERVICE_PORT=3306 \
-e MYSQL_SERVICE_USER=root \
-e MYSQL_SERVICE_PASSWORD=qq123 \
-e MYSQL_SERVICE_DB_NAME=nacos_config \
-e NACOS_SERVER_IP=192.168.163.21 \
--net mynet \
--ip 192.168.163.21 \
--name nacos1 \
-v /root/nacos/nacos1/init.d/custom.properties:/home/nacos/init.d/custom.properties \
-v /root/nacos/nacos1/logs:/home/nacos/logs \
nacos/nacos-server
```

```
docker run -itd \
-e PREFER_HOST_MODE=hostname \
-e MODE=cluster \
-e NACOS_APPLICATION_PORT=8848 \
-e NACOS_SERVERS="192.168.163.21:8848 192.168.163.22:8848 192.168.163.23:8848" \
-e SPRING_DATASOURCE_PLATFORM=mysql \
-e MYSQL_SERVICE_HOST=1.116.106.46 \
-e MYSQL_SERVICE_PORT=3306 \
-e MYSQL_SERVICE_USER=root \
-e MYSQL_SERVICE_PASSWORD=qq123 \
-e MYSQL_SERVICE_DB_NAME=nacos_config \
-e NACOS_SERVER_IP=192.168.163.22 \
--net mynet \
--ip 192.168.163.22 \
--name nacos2 \
-v /root/nacos/nacos2/init.d/custom.properties:/home/nacos/init.d/custom.properties \
-v /root/nacos/nacos2/logs:/home/nacos/logs \
nacos/nacos-server
```

```
docker run -itd \
-e PREFER_HOST_MODE=hostname \
-e MODE=cluster \
-e NACOS_APPLICATION_PORT=8848 \
-e NACOS_SERVERS="192.168.163.21:8848 192.168.163.22:8848 192.168.163.23:8848" \
-e SPRING_DATASOURCE_PLATFORM=mysql \
-e MYSQL_SERVICE_HOST=1.116.106.46 \
-e MYSQL_SERVICE_PORT=3306 \
-e MYSQL_SERVICE_USER=root \
-e MYSQL_SERVICE_PASSWORD=qq123 \
-e MYSQL_SERVICE_DB_NAME=nacos_config \
-e NACOS_SERVER_IP=192.168.163.23 \
--net mynet \
--ip 192.168.163.23 \
--name nacos3 \
-v /root/nacos/nacos3/init.d/custom.properties:/home/nacos/init.d/custom.properties \
-v /root/nacos/nacos3/logs:/home/nacos/logs \
nacos/nacos-server
```

