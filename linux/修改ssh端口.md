直接在 /etc/ssh/sshd_config文件中添加 Port 【你的端口】

```javascript
# If you want to change the port on a SELinux system, you have to tell
# SELinux about this change.
# semanage port -a -t ssh_port_t -p tcp #PORTNUMBER
Port 22
Port 10022
```

w保存

q退出

重启

```javascript
service sshd restart
```

