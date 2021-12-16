#### 1.CentOS 先安装EPEL 

> EPEL (Extra Packages for Enterprise [Linux](https://so.csdn.net/so/search?from=pc_blog_highlight&q=Linux))是基于Fedora的一个项目，为“红帽系”的操作系统提供额外的软件包，适用于RHEL、CentOS和Scientific Linux.
>

```
sudo yum -y install epel-release
yum repolist //查看仓库
```

#### 2. 安装nodejs

方式一：

```
cd /usr/local/src/
sudo wget https://nodejs.org/dist/v16.13.1/node-v16.13.1-linux-x64.tar.xz
sudo tar xf node-v16.13.1-linux-x64.tar.xz
cd node-v16.13.1-linux-x64/
./bin/node -v //查看版本
```

解压文件的 bin 目录底下包含了 node、npm 等命令，我们可以使用 ln 命令来设置软连接：

```
sudo ln -s /usr/local/src/node-v16.13.1-linux-x64/bin/node /usr/bin/node
node -v 

sudo ln -s /usr/local/src/node-v16.13.1-linux-x64/bin/npm /usr/bin/npm
npm -v
```

方式二（不推荐）：

> epel仓库nodejs版本太老，会导致docsify启动失败

```
sudo yum -y install epel-release //安装epel仓库
yum repolist //查看仓库
sudo yum install nodejs
node -v
npm -v
```

#### 3. 安装docsify-cli工具

> 参考 https://docsify.js.org/#/zh-cn/quickstart

```
# 全局安装 docsify-cli 工具
sudo npm i docsify-cli -g
```

#### 4.初始化项目

```
mkdir docs
docsify init docs
docsify serve docs //启动
```

