package zookeeper.test;

import java.io.IOException;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

public class Test3 {
    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        ZooKeeper client1 = new ZooKeeper("10.15.9.33", 3000, null);
        client1.exists("/sohu/test1", true);
        Stat stat = client1.exists("/sohu", null);
        System.out.println(stat.getVersion());
        Stat statNew = client1.setData("/sohu", "newvale1wewr11122".getBytes(), stat.getVersion());
        System.out.println(statNew.getVersion());
    }
}
