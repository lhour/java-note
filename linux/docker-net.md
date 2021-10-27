```c
[root@VM-4-15-centos ~]# docker run -itd --name spark --rm sequenceiq/spark 
WARNING: IPv4 forwarding is disabled. Networking will not work.
aaf1965ed370eba68d626d6f55fd3e75ef6e9f67e30e57df453e96bc7b9ba273
```

在 /etc/sysctl.conf 中修改 ipv4 = 1

```
[root@VM-4-15-centos etc]# vim sysctl.conf
[root@VM-4-15-centos etc]# systemctl restart network && systemctl restart docker
Failed to restart network.service: Unit network.service not found.
```

重启服务

```
nmcli c reload
```

```
systemctl restart docker
```

