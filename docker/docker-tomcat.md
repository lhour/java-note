docker安装tomcat后发现无法访问页面资源

```
docker run --name tomcat -p 8080:8080 -v $PWD/test:/usr/local/tomcat/webapps/test -d tomcat
```

首先可以检查防火墙问题

```
systemctl status firewalld
```

```
[root@localhost ~]# systemctl status firewalld
● firewalld.service - firewalld - dynamic firewall daemon
   Loaded: loaded (/usr/lib/systemd/system/firewalld.service; enabled; vendor preset: enabled)
   Active: inactive (dead) since 六 2021-08-28 09:03:39 CST; 5 days ago
     Docs: man:firewalld(1)
  Process: 869 ExecStart=/usr/sbin/firewalld --nofork --nopid $FIREWALLD_ARGS (code=exited, status=0/SUCCESS)
 Main PID: 869 (code=exited, status=0/SUCCESS)

8月 28 00:17:23 localhost.localdomain firewalld[869]: WARNING: COMMAND_FAILED: '/usr/sbin/iptables -w10 -t filter -F DOCKER-ISOLATION-STAGE-1...t name.
8月 28 00:17:23 localhost.localdomain firewalld[869]: WARNING: COMMAND_FAILED: '/usr/sbin/iptables -w10 -t filter -X DOCKER-ISOLATION-STAGE-1...t name.
8月 28 00:17:23 localhost.localdomain firewalld[869]: WARNING: COMMAND_FAILED: '/usr/sbin/iptables -w10 -t filter -F DOCKER-ISOLATION-STAGE-2...t name.
8月 28 00:17:23 localhost.localdomain firewalld[869]: WARNING: COMMAND_FAILED: '/usr/sbin/iptables -w10 -t filter -X DOCKER-ISOLATION-STAGE-2...t name.
8月 28 00:17:23 localhost.localdomain firewalld[869]: WARNING: COMMAND_FAILED: '/usr/sbin/iptables -w10 -t filter -F DOCKER-ISOLATION' failed...t name.
8月 28 00:17:23 localhost.localdomain firewalld[869]: WARNING: COMMAND_FAILED: '/usr/sbin/iptables -w10 -t filter -X DOCKER-ISOLATION' failed...t name.
8月 28 00:17:23 localhost.localdomain firewalld[869]: WARNING: COMMAND_FAILED: '/usr/sbin/iptables -w10 -D FORWARD -i docker0 -o docker0 -j D...hain?).
8月 28 00:17:23 localhost.localdomain firewalld[869]: WARNING: COMMAND_FAILED: '/usr/sbin/iptables -w10 -D FORWARD -i docker0 -o docker0 -j D...hain?).
8月 28 09:03:38 localhost.localdomain systemd[1]: Stopping firewalld - dynamic firewall daemon...
8月 28 09:03:39 localhost.localdomain systemd[1]: Stopped firewalld - dynamic firewall daemon.
Hint: Some lines were ellipsized, use -l to show in full.
```

其次查看镜像内部结构

```cmd
[root@localhost ~]# docker exec -it 0cb241f1604c /bin/bash
root@0cb241f1604c:/usr/local/tomcat# ls
BUILDING.txt	 LICENSE  README.md	 RUNNING.txt  conf  logs	    temp     webapps.dist
CONTRIBUTING.md  NOTICE   RELEASE-NOTES  bin	      lib   native-jni-lib  webapps  work
[root@0cb241f1604c:/usr/local/tomcat# cp -r webapps.dist/* ./webapps
[root@0cb241f1604c:/usr/local/tomcat# cd webapps
[root@0cb241f1604c:/usr/local/tomcat/webapps# ls
ROOT  docs  examples  host-manager  manager  test
[root@0cb241f1604c:/usr/local/tomcat/webapps# cd ..
[root@0cb241f1604c:/usr/local/tomcat# rm -rf webapps.dist

```

将webapps.dist内容全部复制到webapps下

然后删除webapps.dist

网页可以正常访问