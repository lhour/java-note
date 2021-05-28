## linux目录层级

1. linux只有一个根目录
2. 层级式目录结构
    * bin ->user/bin:系统可执行文件，可以在任何目录下执行
    * user/local/bin：用户自己的可执行文件
    * etc：存放配置文件。配置环境变量（/etc/profile）
    * home：每一个用户的根目录用来保护私人数据
    * opt：存档额外的安装的软件

## 远程操作
1.xsheel远程连接
2.xftp：远程传输文件

## linux用户管理

1. 系统管理员分发，root账号创建普通账号
2. 添加用户 useradd 用户名
    * useradd lisi
    * |->创建一个用户lisi
    * |->在home目录下创建用户的根目录
    * |->创建一个组，在linux中任何一个用户都至少属于一个组，如果不指定自动创建，用于权限控制
    * |->useradd -d /home/EE wangwu 创建用户同时指定根目录
3. 给用户设置密码：passwd lisi
    * 密码要满足一定的复杂度
4. 删除用户：userdel 用户名 
    * userdel -r 用户名 删除用户同时删除主目录
5. 查看用户信息：id 用户名

