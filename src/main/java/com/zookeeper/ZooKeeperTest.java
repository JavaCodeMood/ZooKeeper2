package com.zookeeper;

import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs.Ids;

public class ZooKeeperTest {
	public static void main(String[] args) throws Exception{
		ZooKeeper zk = new ZooKeeper("1.zk.cdel.local:2181",3000,null);
		System.out.println("=======�����ڵ�=======");
		if(zk.exists("/test", false) == null){
			zk.create("/test", "znode1".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
		}
		System.out.println("=============�鿴�ڵ��Ƿ�װ�ɹ�===============");  
		System.out.println(new String(zk.getData("/test", false, null)));
		
		 System.out.println("=========�޸Ľڵ������==========");  
		 zk.setData("/test", "zNode2".getBytes(), -1);
		 
		 System.out.println("========�鿴�޸ĵĽڵ��Ƿ�ɹ�=========");  
		 System.out.println(new String(zk.getData("/test", false, null)));
		 
		 System.out.println("=======ɾ���ڵ�=========="); 
		 zk.delete("/test", -1);
		 
		 System.out.println("==========�鿴�ڵ��Ƿ�ɾ��============");  
		 System.out.println("�ڵ�״̬��" + zk.exists("/test", false));
		 zk.close();
	}

}
