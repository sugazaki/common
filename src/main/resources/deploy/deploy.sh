#!/bin/bash
source /etc/profile

#用法  deploy.sh {DEPLOY_PATH} {PORT}

DEPLOY_PATH=$1
PORT=$2

#解压后工程目录
SHELL_FULL_PATH=$0
WEBAPP_PATH=${SHELL_FULL_PATH%WEB-INF/classes/deploy/deploy.sh}
WAR_PATH=${WEBAPP_PATH%/}.war

if [ "$DEPLOY_PATH" = "$WEBAPP_PATH" ]; then
  echo "部署路径不能和解压路径相同"
  exit 1
fi

echo "shell path : $SHELL_FULL_PATH"
echo "pwd: `pwd`"

#step1.停止tomcat
lsof -i :$PORT|grep -v "PID"|awk '{print "kill -9",$2}'|sh

#step2.创建部署目录
if [ ! -d $DEPLOY_PATH ];then
  mkdir -p $DEPLOY_PATH
fi

#web app 目录
if [ -d $DEPLOY_PATH/webapp ]; then
  rm -rf $DEPLOY_PATH/webapp
fi
mv $WEBAPP_PATH $DEPLOY_PATH/webapp

rm $WAR_PATH
cd $DEPLOY_PATH

#安装tomcat
bash webapp/WEB-INF/classes/deploy/tomcat/install_tomcat.sh

TOMCAT_CONFIG=tomcat/conf/server.xml
TOMCAT_START=tomcat/bin/startup.sh

#tomcat 配置替换
cp webapp/WEB-INF/classes/deploy/tomcat/server.xml tomcat/conf/server.xml

sed -i s?'${HTTP_PORT}'?$PORT?g $TOMCAT_CONFIG
sed -i s?'${DEPLOY_PATH}'?$DEPLOY_PATH/webapp?g $TOMCAT_CONFIG

#启动相关参数
#export CATALINA_OPTS="-Dfile.encoding=UTF-8 -server -Xms4096m -Xmx4096m -Xmn2048m -XX:PermSize=256m -XX:MaxPermSize=512m -XX:SurvivorRatio=10 -XX:MaxTenuringThreshold=15 -XX:NewRatio=2 -XX:+DisableExplicitGC"

$TOMCAT_START


