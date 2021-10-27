

查看git版本，卸载旧版本(如果没有安装git请直接到下一步)

```
git --version

yum remove git
```

安装依赖软件

```
yum install curl-devel expat-devel gettext-devel openssl-devel zlib-devel asciidoc

yum install gcc perl-ExtUtils-MakeMaker
```

编译安装最新的git版本

```
cd /usr/local/src/

wget -O git.zip https://github.com/git/git/archive/master.zip

unzip git.zip

cd git-master

make prefix=/usr/local/git all

make prefix=/usr/local/git install
```

添加到环境变量

```
echo "export PATH=$PATH:/usr/local/git/bin" >> ~/.bashrc

source ~/.bashrc

git --version
```

