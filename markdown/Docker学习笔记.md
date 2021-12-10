### 1.Typora激活教程

https://suyin-blog.club/2021/3RVTXAY/

### 2.安装Linux虚拟机CentOS

 - 新建用户，建议不要直接使用root，新建一个自己喜欢的用户名，需要root权限的时候sudo。
sudo 的时候出现 is not in the sudoers file解决方法：https://blog.csdn.net/inkfish/article/details/5168676

 - 系统硬件配置VMware界面可以修改，建议CPU和内存配置为本机的一半

 - 如果本机有vpn，建议网络连接选择NAT模式，这样VM走的也是vpn
 无法联网参考 https://blog.csdn.net/sinat_32079337/article/details/70238107

### 3.安装docker

 https://docs.docker.com/engine/install/centos/

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



docker run --name webserver -d -p 80:80 mynginx