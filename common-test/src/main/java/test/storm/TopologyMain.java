package test.storm;

import backtype.storm.Config;
import backtype.storm.StormSubmitter;
import backtype.storm.generated.AlreadyAliveException;
import backtype.storm.generated.InvalidTopologyException;
import backtype.storm.topology.TopologyBuilder;
import backtype.storm.tuple.Fields;



/****
 * docker run -i -t -p 8080:8080 -p 6627:6627 --name=tstorm triompha/storm /bin/bash
 * 
 * ~/Downloads/apache-storm-0.9.4/bin/storm jar common-test-0.0.1-SNAPSHOT.jar test.storm.TopologyMain
 * 
 * main中的文件位置需要是在服务器上的位置
 * 
 * 
 * 
 * @author triompha
 *
 */
public class TopologyMain {
	public static void main(String[] args) throws InterruptedException,
			AlreadyAliveException, InvalidTopologyException {

		// Topology definition
		TopologyBuilder builder = new TopologyBuilder();
		builder.setSpout("word-reader", new WordReader());
		builder.setBolt("word-normalizer", new WordNormalizer())
				.shuffleGrouping("word-reader");
		builder.setBolt("word-counter", new WordCounter(), (Number) 1)
				.fieldsGrouping("word-normalizer", new Fields("word"));

		// Configuration
		Config conf = new Config();
		conf.put(Config.NIMBUS_HOST, "192.168.99.100");
		conf.put(Config.NIMBUS_THRIFT_PORT, 6627);
		conf.put("wordsFile","/opt/storm/words");
		conf.put("outFile", "/opt/storm/words-out");
//		conf.put("wordsFile",args[0]);
//		conf.put("outFile", args[1]);
		conf.setDebug(true);
		// Topology run
		conf.put(Config.TOPOLOGY_MAX_SPOUT_PENDING, 1);

		conf.setNumWorkers(3);
		StormSubmitter.submitTopology("Getting-Started-Toplogie", conf,
				builder.createTopology());

		// LocalCluster cluster = new LocalCluster();
		// cluster.submitTopology("Getting-Started-Toplogie", conf,
		// builder.createTopology());
		// Thread.sleep(1000);
		// cluster.shutdown();

	}
}
