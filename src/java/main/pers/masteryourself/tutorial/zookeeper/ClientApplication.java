package pers.masteryourself.tutorial.zookeeper;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.util.concurrent.CountDownLatch;

/**
 * <p>description : ClientApplication
 *
 * <p>blog : https://blog.csdn.net/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2020/4/19 11:02
 */
public class ClientApplication {

    public static void main(String[] args) throws Exception {
        final CountDownLatch latch = new CountDownLatch(1);
        ZooKeeper zooKeeper = new ZooKeeper("192.168.89.210:2181", 5000, watchedEvent -> {
            if (Watcher.Event.KeeperState.SyncConnected == watchedEvent.getState()) {
                System.out.println("zk 连接成功");
                latch.countDown();
            }
        });
        latch.await();
        zooKeeper.create("/temp", "temp".getBytes(), null, CreateMode.EPHEMERAL);
    }

}
