### 1.Typora激活教程

https://suyin-blog.club/2021/3RVTXAY/

### 2.安装Linux虚拟机CentOS

https://www.runoob.com/w3cnote/vmware-install-centos7.html

 - 新建用户，建议不要直接使用root，新建一个自己喜欢的用户名，需要root权限的时候sudo。
sudo 的时候出现 is not in the sudoers file解决方法：https://blog.csdn.net/inkfish/article/details/5168676

 - 系统硬件配置VMware界面可以修改，建议CPU和内存配置为本机的一半

 - 如果本机有vpn，建议网络连接选择NAT模式，这样VM走的也是vpn
 无法联网参考 https://blog.csdn.net/sinat_32079337/article/details/70238107

### 3.安装docker

 https://docs.docker.com/engine/install/centos/

### 4.添加docker用户组

方式一：

```
sudo vim /etc/group
找到docker用户组
例如：
docker:x:982:
在最后一行加上你要加入的用户名,多个逗号隔开
docker:x:982:bigtotoro
输入:wq或者:x保存
切换用户，在切回来
su -
su bigtotoro
```

方式二：

https://blog.csdn.net/point0mine/article/details/79448402

### 4.安装git并配置github

 https://www.cnblogs.com/woider/p/6533709.html

### 5.Docker上下文
https://blog.csdn.net/qianghaohao/article/details/87554255

例如工程结构：

```
helloworld-app
├── Dockerfile
└── docker
    ├── app-1.0-SNAPSHOT.jar
    ├── hello.txt
    └── html
        └── index.html
```

```
docker build -f Dockerfile -t hello-app:1.0 docker 

-f 是在当前hellowworld-app目录下找Dockfile文件
docker参数是表示要推送给docker deamon的上下文路径

如果Dockerfile在docker目录， 那么可以用
docker build -t hello-app:1.0 docker 
这时因为指定了docker为上下文，不带-f时，会默认从上下文中查找Dockerfile文件
如果cd docker目录，也可以用
docker build -t hello-app:1.0 . 
```



```
DockerFile内容：

FROM busybox
COPY hello.txt .
COPY html/index.html .

可以运行成功， 因为docker目录就是这3条命令运行的上下文
COPY hello.txt . 表示将docker/hello.txt复制到容器的当前目录, 也就是根目录
```

### 6.docker 镜像命令

```
# 从docker hub中获取镜像, 不带版本号就是latest
docker pull ubuntu:18.04

# 查看镜像
docker images

# 为本地镜像添加tag标签
docker tag ubuntu:latest myubuntu:latest
//myubuntu:latest与ubuntu:latest的image id是相同的，它们实际上指向了同一个镜像文件，只是别名不同而巳。docker tag命令添
加的标签实际上起到了类似链接的作用

# 使用标签删除镜像
docker rmi myubuntu:latest
// 因为myubuntu:latest实际上是ubuntu:latest的标签，所以删除并不会影响ubuntu:latest。当一个image id只有一个镜像标签，使用此命令就会彻底删除镜像
docker rmi ba6acccedd29
//ba6acccedd29是镜像id，与使用标签删除镜像不同的时，如果有多个标签指向这个镜像id，那么会删除失败，可以使用 -f 强制删除，但是不推荐

# 导出镜像
docker save -o ubuntu_18.04.tar ubuntu:18.04

# 导入镜像
docker load < ubuntu_18.04.tar
// 删除原有镜像，重新导入，新导入的image id与原来的一样

# 上传镜像
docker login //先登录docker
docker tag helloworld:1.0 ${user}/helloword:1.0 //为本地镜像打上带docker用户名的tag
docker push sky623966322/helloword:1.0
```