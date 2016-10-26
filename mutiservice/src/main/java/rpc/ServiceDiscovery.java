package rpc;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event;
import org.apache.zookeeper.ZooKeeper;

import io.netty.util.internal.ThreadLocalRandom;

/**
 *Rpc 服务发现
 * @author zhangwei_david
 * @version $Id: ServiceDiscovery.java, v 0.1 2014年12月31日 下午9:10:23 zhangwei_david Exp $
 */
public class ServiceDiscovery {
  // 日志
  private static final Logger   logger   = LogManager.getLogger(ServiceDiscovery.class);
  private CountDownLatch        latch   = new CountDownLatch(1);
  private volatile List<String> dataList = new ArrayList<String>();
  private String                registryAddress;
  public void init() {
    System.err.println("Rpc 服务发现初始化...");
    ZooKeeper zk = connectServer();
    if (zk != null) {
      watchNode(zk);
    }
  }
  public String discover() {
    String data = null;
    int size = dataList.size();
    if (size > 0) {
      if (size == 1) {
        data = dataList.get(0);
      } else {
        data = dataList.get(ThreadLocalRandom.current().nextInt(size));
      }
    }
    return data;
  }
  private ZooKeeper connectServer() {
    ZooKeeper zk = null;
    try {
      zk = new ZooKeeper(registryAddress, Constant.DEFAULT_ZK_SESSION_TIMEOUT, new Watcher() {
        public void process(WatchedEvent event) {
          if (event.getState() == Event.KeeperState.SyncConnected) {
            latch.countDown();
          }
        }
      });
      latch.await();
    } catch (Exception e) {
    }
    return zk;
  }
  private void watchNode(final ZooKeeper zk) {
    try {
      List<String> nodeList = zk.getChildren(Constant.ROOT, new Watcher() {
        public void process(WatchedEvent event) {
          if (event.getType() == Event.EventType.NodeChildrenChanged) {
            watchNode(zk);
          }
        }
      });
      List<String> dataList = new ArrayList<String>();
      for (String node : nodeList) {
        byte[] bytes = zk.getData(Constant.ROOT + node, false, null);
        dataList.add(new String(bytes));
      }
      this.dataList = dataList;
      if (dataList.isEmpty()) {
        throw new RuntimeException("尚未注册任何服务");
      }
    } catch (Exception e) {
    }
  }
  /**
   * Setter method for property <tt>registryAddress</tt>.
   *
   * @param registryAddress value to be assigned to property registryAddress
   */
  public void setRegistryAddress(String registryAddress) {
    this.registryAddress = registryAddress;
  }
}
