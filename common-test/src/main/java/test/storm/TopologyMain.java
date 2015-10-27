package test.storm;
import backtype.storm.Config;
import backtype.storm.StormSubmitter;
import backtype.storm.generated.AlreadyAliveException;
import backtype.storm.generated.InvalidTopologyException;
import backtype.storm.topology.TopologyBuilder;
import backtype.storm.tuple.Fields;


public class TopologyMain {
	public static void main(String[] args) throws InterruptedException, AlreadyAliveException, InvalidTopologyException {
         
        //Topology definition
		TopologyBuilder builder = new TopologyBuilder();
		builder.setSpout("word-reader",new WordReader());
		builder.setBolt("word-normalizer", new WordNormalizer())
			.shuffleGrouping("word-reader");
		builder.setBolt("word-counter", new WordCounter(),(Number)1)
			.fieldsGrouping("word-normalizer", new Fields("word"));
		
        //Configuration
		Config conf = new Config();
		conf.put(Config.NIMBUS_HOST, "192.168.169.106");
		conf.put(Config.NIMBUS_THRIFT_PORT, 6627);
		conf.put("wordsFile", args[0]);
		conf.put("outFile", args[1]);
		conf.setDebug(true);
        //Topology run
		conf.put(Config.TOPOLOGY_MAX_SPOUT_PENDING, 1);
		
	    conf.setNumWorkers(3);
	    StormSubmitter.submitTopology("Getting-Started-Toplogie", conf, builder.createTopology());
	      
//		LocalCluster cluster = new LocalCluster();
//		cluster.submitTopology("Getting-Started-Toplogie", conf, builder.createTopology());
//		Thread.sleep(1000);
//		cluster.shutdown();
	    
	}
}
