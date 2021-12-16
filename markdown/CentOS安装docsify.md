#### 1. 安装nodejs

方式一：

> 访问 https://nodejs.org/en/download/ 找到最新版本的链接

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
sudo yum -y install epel-release //为CentOS安装epel仓库
yum repolist //查看仓库
sudo yum install nodejs
node -v
npm -v
```

#### 2. 安装docsify-cli工具

> 参考 https://docsify.js.org/#/zh-cn/quickstart

```
# 全局安装 docsify-cli 工具
sudo npm i docsify-cli -g
```

#### 3.初始化项目

```
mkdir docs
docsify init docs
docsify serve docs //启动
```

4.访问网页

```
http://localhost:3000
```

