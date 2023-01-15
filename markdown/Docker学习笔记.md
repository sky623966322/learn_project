### 1.Typora激活教程

https://suyin-blog.club/2021/3RVTXAY/

### 2.安装Linux虚拟机CentOS

[https://www.runoob.com/w3cnote/vmware-install-centos7.html](https://www.runoob.com/w3cnote/vmware-install-centos7.html)

注意事项：

 - 新建用户，建议不要直接使用root，新建一个自己喜欢的用户名，需要root权限的时候sudo。
sudo 的时候出现 is not in the sudoers file解决方法：https://blog.csdn.net/inkfish/article/details/5168676

 - 系统硬件配置VMware界面可以修改，建议CPU和内存配置为本机的一半

 - 如果本机有vpn，建议网络连接选择NAT模式，这样VM走的也是vpn
 无法联网参考 https://blog.csdn.net/sinat_32079337/article/details/70238107

### 3.安装docker

 [https://docs.docker.com/engine/install/centos/]( https://docs.docker.com/engine/install/centos/)

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

[https://blog.csdn.net/point0mine/article/details/79448402](https://blog.csdn.net/point0mine/article/details/79448402)

### 4.安装git并配置github

 https://www.cnblogs.com/woider/p/6533709.html

### 5.Docker上下文
[https://blog.csdn.net/qianghaohao/article/details/87554255](https://blog.csdn.net/qianghaohao/article/details/87554255)

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

- 从docker hub中获取镜像, 不带版本号就是latest

```
docker pull ubuntu:18.04
```

- 查看镜像

```
docker images //查看全部镜像
docker images ubuntu //查看所有版本的ubuntu镜像
```

- 为本地镜像添加tag标签

```
docker tag ubuntu:latest myubuntu:latest
//myubuntu:latest与ubuntu:latest的image id是相同的，它们实际上指向了同一个镜像文件，只是别名不同而巳。docker tag命令添
加的标签实际上起到了类似链接的作用
```

- 使用标签删除镜像

```
docker rmi myubuntu:latest
// 因为myubuntu:latest实际上是ubuntu:latest的标签，所以删除并不会影响ubuntu:latest。当一个image id只有一个镜像标签，使用此命令就会彻底删除镜像
docker rmi ba6acccedd29
//ba6acccedd29是镜像id，与使用标签删除镜像不同的时，如果有多个标签指向这个镜像id，那么会删除失败，可以使用 -f 强制删除，但是不推荐
```

- 导出镜像

```
docker save -o ubuntu_18.04_image1 ubuntu:18.04
或
docker save ubuntu:18.04 > ubuntu_18.04_image2.tar
```

- 导入镜像

```
docker load -i ubuntu_18.04.tar
或
docker load < ubuntu_18.04.tar
// 删除原有镜像，重新导入，新导入的image id与原来的一样
```

- 上传镜像

```
docker login //先登录docker
docker tag helloworld:1.0 ${user}/helloword:1.0 //为本地镜像打上带docker用户名的tag
docker push sky623966322/helloword:1.0
```

- 查看镜像制作历史

  ```
  docker history ubuntu:18.04
  ```

### 7.docker 容器命令

#### 创建容器

- 创建容器

  ```
  # 新建容器
  docker create -it ubuntu:latest
  # 查看所有容器
  docker ps -a
  # 启动容器
  docker start 89855bd795a4
  ```

- 新建并启动容器（相当于上面三步）

  ```
  docker run -it ubuntu:18.04 /bin/bash //启动并启动bash终端
  docker run -it -d ubuntu:latest //启动后台运行
  
  run命令，如果本地没有镜像，会从公共仓库下载。执行用户指定的应用程序；
  -t 让docker分配一个伪终端并绑定到容器的标准输入上，-i则让容器的标准输入保持打开， /bin/bash启动bash终端。
  -d 后台运行
  exit //退出容器
  ```

- 守护态运行

  ```
  docker run -d ubuntu:latest /bin/sh -c "while true;do echo hello world; sleep 1; done"
  ```

#### 停止容器

- 暂停容器

  ```
  docker pause xxxx
  docker unpause xxx
  ```

- 终止容器

  ```
  docker stop xxx
  docker start xxx
  docker restart xxx
  ```


- 进入容器

  ```
  docker exec -it 358853f0a7bb bash
  ```

- 删除容器

  ```
  docker rm [-f] 358853f0a7bb
  -f 表示强制删除
  ```

##### 导入和导出容器

- 导出容器

  ```
  docker export -o ubuntu_latest_container1.tar d09841ea1e8c
  或
  docker export d09841ea1e8c > ubuntu_latest_container2.tar
  ```

- 导入容器

  ```
  docker import ubuntu_latest_container1.tar test/ubuntu:v1.0
  
  [bigtotoro@bigtotoro ~]$ docker images
  REPOSITORY               TAG       IMAGE ID       CREATED         SIZE
  test/ubuntu              v1.0      4d004593f939   6 seconds ago   72.8MB
  ```

  > 用户既可以使用 docker load 来导入镜像存储文件到本地镜像库，也可以使用 docker import 来导入一个容器快照到本地镜像库。这两者的区别在于容器快照文件将丢弃所有的历史记录和元数据信息（即仅保存容器当时的快照状态），而镜像存储文件将保存完整记录，体积也要大。此外，从容器快照文件导入时可以重新指定标签等元数据信息。

#### 查看容器

- 查看容器详情

  ```
  docker inspect d09841ea1e8c
  [
      {
          "Id": "d09841ea1e8c920b801f27ca4e1e64b4baa1eae30c23e0753ffa9e7c388dd598",
          "Created": "2021-12-22T14:54:35.113064643Z",
          "Path": "bash",
          "Args": [],
          "State": {
              "Status": "running",
              "Running": true,
              "Paused": false,
              "Restarting": false,
  ...
  ]
  ```

- 查看容器内进程

  ```
  docker top d09841ea1e8c
  
  bigtotoro@bigtotoro ~]$ docker top d09841ea1e8c
  UID                 PID                 PPID                C                   STIME               TTY                 TIME                CMD
  root                36290               36270               0                   08:29               ?                   00:00:00            bash
  
  ```

- 查看统计信息

  ```
  docker stats d09841ea1e8c
  
  CONTAINER ID   NAME           CPU %     MEM USAGE / LIMIT   MEM %     NET I/O       BLOCK I/O   PIDS
  d09841ea1e8c   nifty_euclid   0.00%     536KiB / 3.682GiB   0.01%     1.19kB / 0B   0B / 0B     1
  ```

#### 其他容器命令

- 复制文件

  ```
  docker cp docs d09841ea1e8c:/tmp   //cp 命名可以将主机文件复制到容器内部
  docker exec -it d09841ea1e8c bash  //进入容器内部
  ll /tmp //查看复制进来的文件 
  ```

- 查看容器变更记录

  ```
  docker diff d09841ea1e8c
  
  bigtotoro@bigtotoro ~]$ docker diff d09841ea1e8c
  C /root
  A /root/.bash_history
  C /tmp
  A /tmp/docs
  A /tmp/docs/.nojekyll
  A /tmp/docs/CentOS安装docsify.md
  A /tmp/docs/README.md
  A /tmp/docs/index.html
  ```

- 查看端口映射

  ```
  docker pull nginx:latest //拉取nginx镜像
  docker run -it -d -p 8088:80 nginx:latest //将主机8088映射到容器80端口，并后台启动
  docker port 2555c5251dd1
  
  [bigtotoro@bigtotoro ~]$ docker port 2555c5251dd1
  80/tcp -> 0.0.0.0:8088
  80/tcp -> :::8088
  ```

### docker 镜像仓库

- docker镜像仓库分为公共镜像仓库，例如docker hub，还有华为云、阿里云等第三方镜像仓库；
- 可以使用registry镜像搭建私有镜像仓库；

### docker卷与持久化

> 卷 - volume ，对应的是主机的一个目录，可以将卷挂载到容器的目录。
>
> - 卷可以被容器共享；
> - 对卷内的数据修改，主机和容器立即生效；

- 创建卷

  ```
  docker volume create [local] myvol //local表示使用内置的local驱动，可以省略。本地卷只能被所在的节点容器使用
  docker volume ls //查看卷列表
  docker volume inspect myvol //查看卷
  
  [bigtotoro@bigtotoro ~]$ docker volume inspect myvol
  [
      {
          "CreatedAt": "2021-12-25T22:08:03+08:00",
          "Driver": "local",
          "Labels": {},
          "Mountpoint": "/var/lib/docker/volumes/myvol/_data",
          "Name": "myvol",
          "Options": {},
          "Scope": "local"
      }
  ]
  Mountpoint 表示卷位于主机的具体目录。/var/lib/docker/volumes为local驱动默认目录。
  ```

- 删除卷

  ```
  docker volume rm myvol
  ```

- 挂载卷

  ```
  docker run -it -d --name voltainer --mount source=bizvol,target=/vol alpine:latest
  
  解释：
  --mount 表示将创建bizvol卷；
  创建alpine:latest镜像所新创建的名为 voltainer 容器；
  并将 bizvol 卷挂载到 voltainer 容器的/vol目录；
  
  # 进入容器
  docker exec -it voltainer sh
  
  # 写入文件
  echo "test" >>  /vol/file1
  
  # exit退出容器后查看主机文件
  sudo cat /var/lib/docker/volumes/bizvol/_data/file1
  
  # 删除容器，并查看 bizvol 卷是否存在
  docker rm -f voltainer
  docker volume ls
  
  # 创建新容器，将已创建的 bizvol 卷挂载
  docker run -it -d --name hellocat --mount source=bizvol,target=/vol alpine:latest
  
  # 进入新容器，查看卷数据是否已经挂载
  docker exec -it hellocat sh
  ls /vol
  ```


### docker端口映射与容器互联

- 端口映射

  ```
  docker run -d -p 5000:5000 training/webapp python app.py //主机端口:容器端口
  使用主机端口5000，映射到容器5000端口，以镜像training/webapp创建并启动容器，并且执行 python app.py 命令
  ```

- 容器互联

  ```
  docker run -d --name db training/postgres //创建并启动db容器
  docker run -d -P --name web --link db:db training/webapp python app.py
  
  -P 随机映射一个 49000~49900 的端口到内部容器开放的网络端口
  --link 参数的格式为--link name: alias, 其中name是要链接的容器的名称 ，alias是别名
  ```


操作系统镜像

- BusyBox 集成了100多个最常用的Linux命令，例如cat、echo、grep、telnet等工具。最大的特点是镜像不到2M。
