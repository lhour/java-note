更新 yum

```
sudo yum update
```

卸载旧版

```
sudo yum remove docker  docker-common docker-selinux docker-engine
```

yum-util 提供yum-config-manager功能，另外两个是devicemapper驱动依赖的

```
sudo yum install -y yum-utils device-mapper-persistent-data lvm2
```

设置镜像源

```
sudo yum-config-manager \
    --add-repo \
    http://mirrors.aliyun.com/docker-ce/linux/centos/docker-ce.repo
```

安装最新版本的 Docker Engine-Community 和 containerd

```
sudo yum install docker-ce docker-ce-cli containerd.io
```

