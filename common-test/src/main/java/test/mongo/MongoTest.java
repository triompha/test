package test.mongo;


import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoException;
import com.mongodb.MongoOptions;
import com.mongodb.ReadPreference;
import com.mongodb.ReplicaSetStatus;
import com.mongodb.ServerAddress;

/**
 * MongoDB 连接器
 * 
 */
public class MongoTest {

	private static Mongo[] mongos = new Mongo[10];

	private static short mongoCount = 0;

	private static DB db = null;
	static Mongo m;  
	static Mongo mread;  
    
    static {   
        List<ServerAddress> addresslist = new ArrayList<ServerAddress>();   
        try {   
            addresslist.add(new ServerAddress("10.22.10.129:27017"));   
        } catch (UnknownHostException e) {   
            System.err.println("address check error.");   
            System.exit(-1);   
        }   
           
        List<ServerAddress> readAddresslist = new ArrayList<ServerAddress>();   
        try {   
        	readAddresslist.add(new ServerAddress("10.22.10.129:27017"));      
        } catch (UnknownHostException e) {   
            System.err.println("address check error.");   
            System.exit(-1);   
        }   
        
        MongoOptions options = new MongoOptions();   
        options.autoConnectRetry = true;   
        options.connectionsPerHost = 20;   
        options.connectTimeout = 6000;   
        options.maxAutoConnectRetryTime = 12000;   
        options.maxWaitTime = 12000;   
        options.socketKeepAlive = true;   
        options.socketTimeout = 2000;   
           
        try {   
            m = new Mongo(addresslist, options);   
            mread=new Mongo(readAddresslist,options);
        } catch (MongoException e) {   
            System.err.println("mongo create error.");   
            System.exit(-1);   
        }   
    }  
	public final static Mongo getMongo() {
		return m;
	}

	public final static DB getDB(String dbName) {
		Mongo mongo = getMongo();
		if (db == null && mongo != null) {
			try {
				db = mongo.getDB(dbName);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		return db;
	}
	
	public static void main(String[] args) {
		 Mongo mongo= getMongo() ;
		 ReplicaSetStatus replicaSetStatus=mongo.getReplicaSetStatus();
		 ReadPreference readPreference =mongo.getReadPreference();
		DB readDb = mread.getDB("testdb");
		DB db=getDB("testdb");
		DBCollection  dBCollection =db.getCollection("testcol");
		
		DBObject obj = new BasicDBObject();
		obj.put("id", 6);
//		
//		for(int i=0 ; i <10;i++){
//			obj= new BasicDBObject();
//			obj.put("id", 1+i);
//			obj.put("name", "test"+i);
//			obj.put("age", 22);
//			dBCollection.save(obj);
//		}
		
		
		
		
		
		DBCursor  dBCursor =dBCollection.find();
		while(dBCursor.hasNext()){
			 DBObject  dBObject=dBCursor.next();
			 System.out.println(dBObject.get("name")+" "+dBObject.get("age"));
		}
		System.out.println("----------");
		
		
		dBCursor = dBCollection.find(obj);
		while(dBCursor.hasNext()){
			 DBObject  dBObject=dBCursor.next();
			 System.out.println(dBObject.get("name")+" "+dBObject.get("age"));
		}
		
		System.out.println("------mmmmmmmmmmmmmmmmmmmmmmmm----");
		
		
		
		//另一种简单的链接方式
		DBCollection  rdBCollection =db.getCollection("testcol");
		DBCursor  rdBCursor =dBCollection.find();
		while(rdBCursor.hasNext()){
			 DBObject  dBObject=rdBCursor.next();
			 System.out.println(dBObject.get("name")+" "+dBObject.get("age"));
		}
		try {
			Mongo m2 = new Mongo("10.22.10.129:27017");
			DB db2=m2.getDB("testdb");
			db2.slaveOk();
			DBCollection c2=db2.getCollection("testcol");
			DBCursor  dBCursor2 =c2.find();
			//Exception in thread "main" com.mongodb.MongoException: not talking to master and retries used up
			//加db2.slaveOk();解决。
			while(dBCursor2.hasNext()){
				 DBObject  dBObject=dBCursor2.next();
				 System.out.println(dBObject.get("name")+" "+dBObject.get("age"));
			}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

}
