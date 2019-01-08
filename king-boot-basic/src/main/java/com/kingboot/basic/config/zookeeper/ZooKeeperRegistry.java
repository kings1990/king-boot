package com.kingboot.basic.config.zookeeper;


import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.serialize.BytesPushThroughSerializer;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ZooKeeperRegistry {
    
    @Value ("${zk.address}")
    private String zkServers;
    
    @Value ("${zk.sessionTimeout}")
    private Integer zkSessionTimeout;
    
    @Value ("${zk.connectionTimeout}")
    private Integer zkConnectionTimeout;
    
    @Bean
    private ZooKeeper initZooKeeper() throws Exception {
        Watcher watcher = watchedEvent -> System.out.println(watchedEvent);
        return new ZooKeeper(zkServers, zkSessionTimeout, watcher);
    }
    
    
    @Bean
    private ZkClient initZKClient() throws Exception {
        BytesPushThroughSerializer bytesPushThroughSerializer = new BytesPushThroughSerializer();
        return new ZkClient(zkServers, zkSessionTimeout, zkConnectionTimeout, bytesPushThroughSerializer);
    }
}
