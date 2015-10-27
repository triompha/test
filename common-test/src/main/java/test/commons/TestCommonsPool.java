package test.commons;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.pool.BaseKeyedPoolableObjectFactory;
import org.apache.commons.pool.impl.GenericKeyedObjectPool;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;


public class TestCommonsPool {

    public static void main(String[] args) throws Exception {
        System.out.println("wukong-app".hashCode() % 256);
        
        final List<String> ips = new ArrayList<String>();
        ips.add("10.125.50.61");
        ips.add("10.125.49.101");
        
        @SuppressWarnings({ "unchecked", "rawtypes" })
        GenericKeyedObjectPool<String, String> pool1 = new GenericKeyedObjectPool<>(new BaseKeyedPoolableObjectFactory<String, String>() {

            @Override
            public String makeObject(String key) throws Exception {
                return key+System.currentTimeMillis();
            }
            
            @Override
            public boolean validateObject(String key, String obj) {
                return ips.contains(key);
            }
            
        });
       
        System.out.println(pool1.borrowObject("xxx"));
        Thread.currentThread().sleep(8000);
        System.out.println(pool1.borrowObject("xxx"));
        Thread.currentThread().sleep(8000);
        System.out.println(pool1.borrowObject("xxx"));
        Thread.currentThread().sleep(8000);
        System.out.println(pool1.borrowObject("xxx"));
        
//        GenericKeyedObjectPool pool = new GenericKeyedObjectPool(new BaseKeyedPoolableObjectFactory(){ 
//            @Override 
//            public Object makeObject(Object o) throws Exception { 
//                    return o; 
//            } 
//    }); 

    }
}
