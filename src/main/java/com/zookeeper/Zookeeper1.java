package com.zookeeper;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

public class Zookeeper1 {
	private static String connectString ="1.zk.cdel.local:2181";
	private static int sessionTimeout = 999999;
	
	public static void main(String[] args) throws Exception{
		Watcher watcher = new Watcher(){
			public void process(WatchedEvent event){
				System.out.println("监听到的事件：" + event + "\n");
			}
		};
		final ZooKeeper zookeeper = new ZooKeeper(connectString,sessionTimeout,watcher);
		System.out.println("\n获得连接：" + zookeeper);
		final byte[] data = zookeeper.getData("/com.zookeeper", watcher, null);
	    System.out.println("\n读取的值：" + new String(data));
	    zookeeper.close();
	}

}
