package com.zookeeper1;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs.Ids;  
  
public class ZooKeeperOperator extends AbstractZooKeeper {  
      
    private static Log log = LogFactory.getLog(ZooKeeperOperator.class.getName());  
  
    /** 
     *  
     *<b>function:</b>�����־�̬��znode,��֧�ֶ�㴴��.�����ڴ���/parent/child�������,��/parent.�޷�ͨ�� 
     *@param path 
     *@param data 
     *@throws KeeperException 
     *@throws InterruptedException 
     */  
    public void create(String path,byte[] data)throws KeeperException, InterruptedException{  
        /** 
         * �˴����õ���CreateMode��PERSISTENT  ��ʾThe znode will not be automatically deleted upon client's disconnect. 
         * EPHEMERAL ��ʾThe znode will be deleted upon the client's disconnect. 
         */   
        this.zooKeeper.create(path, data, Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);  
    }  
    /** 
     *  
     *<b>function:</b>��ȡ�ڵ���Ϣ 
     *@param path 
     *@throws KeeperException 
     *@throws InterruptedException 
     */  
    public void getChild(String path) throws KeeperException, InterruptedException{     
        try{  
            List<String> list=this.zooKeeper.getChildren(path, false);  
            if(list.isEmpty()){  
                log.debug(path+"��û�нڵ�");  
            }else{  
                log.debug(path+"�д��ڽڵ�");  
                for(String child:list){  
                    log.debug("�ڵ�Ϊ��"+child);  
                }  
            }  
        }catch (KeeperException.NoNodeException e) {  
            // TODO: handle exception  
             throw e;     
  
        }  
    }  
      
    public byte[] getData(String path) throws KeeperException, InterruptedException {     
        return  this.zooKeeper.getData(path, false,null);     
    }    
      
     public static void main(String[] args) {  
         try {     
                ZooKeeperOperator zkoperator    = new ZooKeeperOperator();     
                zkoperator.connect("1.zk.cdel.local");  
                  
                byte[] data = new byte[]{'a','b','c','d'};     
                     
//              zkoperator.create("/root",null);     
//              System.out.println(Arrays.toString(zkoperator.getData("/root")));     
//                   
//              zkoperator.create("/root/child1",data);     
//              System.out.println(Arrays.toString(zkoperator.getData("/root/child1")));     
//                   
//              zkoperator.create("/root/child2",data);     
//              System.out.println(Arrays.toString(zkoperator.getData("/root/child2")));     
                     
                String zktest="ZooKeeper��Java API����";  
                zkoperator.create("/root/child3", zktest.getBytes());  
                log.debug("��ȡ���õ���Ϣ��"+new String(zkoperator.getData("/root/child3")));  
                  
                System.out.println("�ڵ㺢����Ϣ:");     
                zkoperator.getChild("/root");     
                     
                zkoperator.close();     
                  
                  
            } catch (Exception e) {     
                e.printStackTrace();     
            }     
  
    }  
}  