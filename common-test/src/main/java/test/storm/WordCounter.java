package test.storm;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.SerializationUtils;

import backtype.storm.task.TopologyContext;
import backtype.storm.topology.BasicOutputCollector;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseBasicBolt;
import backtype.storm.tuple.Tuple;

public class WordCounter extends BaseBasicBolt {
    
    public static void main(String[] args) {
        Map map = new HashMap();
        byte[] code =SerializationUtils.serialize((Serializable) map);
        for(byte b : code){
            System.out.print(b);
        }
        System.out.println(new String(code));
    }

	Integer id;
	String name;
	Map<String, Integer> counters;
	
	private FileWriter fileWriter;

	/**
	 * At the end of the spout (when the cluster is shutdown
	 * We will show the word counters
	 */
	@Override
	public void cleanup() {
		System.out.println("-- Word Counter ["+name+"-"+id+"] --");
		for(Map.Entry<String, Integer> entry : counters.entrySet()){
			System.out.println(entry.getKey()+": "+entry.getValue());
		}
		if(fileWriter != null){
		    try {
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
		}
	}

	/**
	 * On create 
	 */
	@Override
	public void prepare(Map stormConf, TopologyContext context) {
		this.counters = new HashMap<String, Integer>();
		this.name = context.getThisComponentId();
		this.id = context.getThisTaskId();
		
		try {
            this.fileWriter = new FileWriter((String) stormConf.get("outFile"));
        } catch (IOException e) {
            throw new RuntimeException("Error write file ["+stormConf.get("outFile")+"]");
        }
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {}


	@Override
	public void execute(Tuple input, BasicOutputCollector collector) {
		String str = input.getString(0);
		/**
		 * If the word dosn't exist in the map we will create
		 * this, if not We will add 1 
		 */
		if(!counters.containsKey(str)){
			counters.put(str, 1);
		}else{
			Integer c = counters.get(str) + 1;
			counters.put(str, c);
		}
		
		System.out.println("Thread " + Thread.currentThread().getName() + " log counters===================" + counters);
		
		if(this.fileWriter != null){
		    try {
                fileWriter.write("Thread " + Thread.currentThread().getName() + " log counters===================" + counters);
                fileWriter.write("\r\n");
                fileWriter.write("====================================================");
                fileWriter.write("\r\n");
                fileWriter.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
		}
	}
}
