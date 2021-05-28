## 安装软件包
1. 官网下载linux版本
2. 使用 rpm -ivh xxx.rpm 安装

## vi和vim linux中的文本编辑器
1. 用来在linux中查看编辑文本文件,就好像windows中的记事本一样
2. vim是vi的增强版
   
### vivim使用
1. mkdir test 创建一个文件目录
2. vim myvim.txt 使用vim创建一个文本
3. 三种模式
   1. 一般模式,可查看文件内容,不能编辑内容,没有权限
   2. 编辑模式,在一般模式下,按下i,a,I,A,进入编辑模式,可以编辑,但是不能保存内容,编辑模式按esc回到一般模式
   3. 命令模式,在一般模式下,按下shift+:进入命令模式
      1. q!  不保存,强制退出编辑器
      2. wq! 保存并且退出
4. 常见快捷键
   1. yy  一般模式下,复制当前行
   2. p 粘贴到光标所在下一行
   3. 5yy  一般模式下,复制当前6行
   4. /关键字,命令模式下,回车进行查找
   5. dd删除光标所在当前行
   6. u撤销上次操作
   7. set nu 显示行号
   8. set nonu

### ls
在 Linux 中我们可以使用 ll 或者 ls –l 命令来显示一个文件的属性以及文件所属的用户和组，如：

实例中，bin 文件的第一个属性用 d 表示。d 在 Linux 中代表该文件是一个目录文件。
<pre>[root@localhost ~]# ls -l
总用量 8
-rw-------. 1 root root 2252 5月  20 17:57 anaconda-ks.cfg
-rw-r--r--. 1 root root 1572 12月  1 2016 CentOS-Base.repo
drwxr-xr-x. 3 root root   18 5月  28 10:20 <font color="#005FFF">Desktop</font>
drwxr-xr-x. 2 root root    6 5月  20 19:58 <font color="#005FFF">Documents</font>
drwxr-xr-x. 2 root root   80 5月  23 12:01 <font color="#005FFF">Downloads</font>
drwxr-xr-x. 2 root root    6 5月  20 19:58 <font color="#005FFF">Music</font>
drwxr-xr-x. 2 root root    6 5月  20 19:58 <font color="#005FFF">Pictures</font>
drwxr-xr-x. 2 root root    6 5月  20 19:58 <font color="#005FFF">Public</font>
drwxr-xr-x. 2 root root    6 5月  20 19:58 <font color="#005FFF">Templates</font>
drwxr-xr-x. 2 root root    6 5月  20 19:58 <font color="#005FFF">Videos</font>
</pre>

1. 在 Linux 中第一个字符代表这个文件是目录、文件或链接文件等等。
    * 当为 d 则是目录
    * 当为 - 则是文件；
    * 若是 l 则表示为链接文档(link file)；
    * 若是 b 则表示为装置文件里面的可供储存的接口设备(可随机存取装置)；
    * 若是 c 则表示为装置文件里面的串行端口设备，例如键盘、鼠标(一次性读取装置)。

2. 1. 第 0 位确定文件类型，
   1. 第 1-3 位确定属主（该文件的所有者）拥有该文件的权限。
   2. 第4-6位确定属组（所有者的同组用户）拥有该文件的权限，
   3. 第7-9位确定其他用户拥有该文件的权限。

3. 其中，第 1、4、7 位表示读权限，如果用 r 字符表示，则有读权限，如果用 - 字符表示，则没有读权限；

4. 第 2、5、8 位表示写权限，如果用 w 字符表示，则有写权限，如果用 - 字符表示没有写权限；第 3、6、9 位表示可执行权限，如果用 x 字符表示，则有执行权限，如果用 - 字符表示，则没有执行权限。 






