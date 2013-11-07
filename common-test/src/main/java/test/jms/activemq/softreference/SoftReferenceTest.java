package test.jms.activemq.softreference;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;

/**
 * test SoftReference useage
 * @author xinchun.wang
 */
public class SoftReferenceTest {
	public static void main(String[] args) throws InterruptedException {
		A a = new A();
		a.src = "aaa";
		ReferenceQueue<A> rq = new ReferenceQueue<A>();
		SoftReference<A> weak = new SoftReference<A>(a, rq);
		a = null;
		System.out.println(weak.get().src); // exist
		
		System.gc(); 
		System.out.println(weak.get().src); // just gc,not use all heap , still exist
		
		cleanHeapFunction(); //casue use all heap
		System.out.println(weak.get()); // not exist
		Reference<? extends A> rs = rq.poll(); //can get instance 
		System.out.println(rs); 
		System.out.println(rs.get());
	}
	
	/**
	 * use all heap ,casuse full gc
	 */
	public static void cleanHeapFunction(){
		try{
			StringBuffer bf = new  StringBuffer();
			for(int i=0;i<10000000000L;i++){
				bf.append(i);
				bf.append(bf);
			}
		}catch(Error e){
			
		}
	}
	
	public static class A{
		protected String src;
		public A(){}
		public String getSrc() {
			return src;
		}
		public void setSrc(String src) {
			this.src = src;
		}
		
	}
}

