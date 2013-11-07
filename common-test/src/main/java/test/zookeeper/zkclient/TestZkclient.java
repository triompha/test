package test.zookeeper.zkclient;

import java.util.List;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.IZkDataListener;






public class TestZkclient {
	
	public static void main(String[] args) {
		
		//生成 ZKClient 单一实例
		ZKClient zkClient = ZKClient.getInstance();
		
		//判断节点是否存在，如果不存在则创建节点
		if(!zkClient.exist(Constant.POOL_PREFIX+"/121212")){
			zkClient.createPersistent(Constant.POOL_PREFIX+"/121212");
		}
		//注意这里写的数据和zookeeper自带的基本client还是有出入的，数据不完全相同。加了前缀
		zkClient.writeData(Constant.POOL_PREFIX+"/121212", "ffff");
		
		//都去zookeeper的数据
		Object obj = zkClient.readData(Constant.POOL_PREFIX+"/121212");
		
		//或去节点的所有子节点名称
		List<String> list =	zkClient.getChildren(Constant.POOL_PREFIX);
		
		//注册节点数据改变事件
		zkClient.subscribeDataChanges(Constant.POOL_PREFIX+"/" + "/121212" , new IZkDataListener() {

			public void handleDataChange(String dataPath, Object data)
					throws Exception {
				System.out.println("data changed of path " +dataPath+"new data is "+ data);
				
			}

			public void handleDataDeleted(String dataPath) throws Exception {
				System.out.println("data deleted of path " +dataPath);
				
			}
		});
		
		//注册字节点列表数据变更事件
		zkClient.subscribeChildChanges(Constant.POOL_PREFIX, new IZkChildListener() {
			
			public void handleChildChange(String path, List<String> newChilds)
					throws Exception {
				System.out.println("data changed of path " +path+"new newChilds is "+ newChilds);
				
			}
		});
		
		
	}

}
