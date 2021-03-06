# 服务项目列表

| 服务                  | 端口 | 访问 |
|:-------------------------:|:-----------:| :--------------------------------------------:|
| nexus                     | 8081        | http://localhost:8081/nexus                   |
| zookeeper                 | 2181 2182   |                                               |
| zkui                      | 9090        | http://localhost:9090                         |
| mysql                     | 3306        |                                               |
| kafka                     | 9092        |                                               |
| kafka monitor             | 8181        | http://localhost:8181                         |
| cas ssl                   | 8443        |                                               |
| basic ssl                 | 443         |                                               |
| jenkins                   | 9999        | http://localhost:9999                         |
| swagger                   | 8083        | http://swagger.ws.com                         |
| static resource           | 80          | http://static.ws.com                          |
| elasticsearch             | 9200        | http://localhost:9200                         |
| zipkin                    | 9411        | http://localhost:9411/zipkin                  |
| rabbitMQ                  | 15672       | http://localhost:15672


| 项目                 | 端口 | 访问 |
|:-------------------------:|:-----------:| :----------------------------------------------:|
| king-boot-timer           | 9100        | http://timer.ws.com                             |
| king-boot-basic           | 8000        | https://king.ws.com/boot/thymeleaf/mappings     |       
| king-boot-admin           | 7000        | http://localhost:7000                           |        
| king-boot-dubbo-server    | 7001        | http://localhost:9001                           |
| king-boot-user-app        | 8301        | http://localhost:8301/api/user/detail/1         |
| king-boot-order-app       | 8302        | http://localhost:8302/api/order/detail/1        |
| king-boot-cionfig-server  | 8202        | http://localhost:8202/master/order-service.json |




#服务启动指令

## zookeeper

```
cd ~/develop/environment/zookeeper/zk34-0/bin 
./zkServer.sh start

cd ~/develop/environment/zookeeper/zk34-1/bin 
./zkServer.sh start

cd ~/develop/environment/zookeeper/zkui-master/target
java -jar zkui-2.0-SNAPSHOT-jar-with-dependencies.jar
```

## mongodb
```
mongod
```

## kafka

```
brew services start kafka
brew services stop kafka
```

## jenkins

```
sudo launchctl load  /Library/LaunchDaemons/org.jenkins-ci.plist
```

## redis

```
cd /usr/local/Cellar/redis/3.0.6/bin
./redis-server
```

## nginx

```
nginx
```

## nexus

```
cd /Users/wilson/develop/nexus/nexus-2.11.3-01-bundle/nexus-2.11.3-01/bin
./nexus start
nginx
```

## elasticsearch
```
cd ~/develop/environment/elasticsearch-6.6.0/bin
./elasticsearch


cd ~/develop/environment/elk/logstash-6.6.0/bin
./logstash -f ../config/beats.conf

cd ~/develop/environment/elk/filebeat-6.6.0
./filebeat -e -c filebeat.yml

cd ~/develop/environment/elk/kibana-6.6.0/bin
./kibana
```

