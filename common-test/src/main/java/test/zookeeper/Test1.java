package test.zookeeper;

import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

public class Test1 {
    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
//        ZooKeeper client1 = new ZooKeeper("10.15.9.33", 3000, null);
//        client1.create("/sohu/test1", "value1".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
    	
    	System.out.println(StringUtils.substringBefore("fff#pianhua", "#pianhua"));
    	
    }
}
