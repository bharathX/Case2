package ai.swim.client;

import ai.swim.model.ModelB;
import recon.Value;
import swim.client.SwimClient;
import java.util.*;

/**
 * Send commands to an instance of service A (uri: a/1) and an instance of service B (b/1)
 * Subscribe to value and map lanes of service a/1 and service b/1 to
 */


public class Client {

    public static void main(String[] args) throws Exception {
        // start the swim client
		System.out.println(System.getProperty("java.io.tmpdir"));
        final SwimClient sc = new SwimClient();
        sc.start();
        final String host = "ws://localhost:9001";

        //runJoinService(sc,host);
        runCsvReaderService(sc, host);

    }


    private static void runCsvReaderService(SwimClient sc, String host) throws InterruptedException {
        // Instantiate Serivce 'CsvReaderService' with filePath as c:\\Data-From-Hoang.csv
        final String nodeUri = "csv/Data-From-Hoang.csv";

		
        // send message to the addLatest command lane to an instance of service CsvReaderService i.e. with id 1
        // since the command lane expects an String, convert the String to Value type
        String filePath;
        filePath = "C://GIT//Data-From-Hoang.csv";
        sc.command(host, nodeUri, "addLatest", Value.of(filePath));

        sc.hostRef(host)
                .nodeRef(nodeUri)
                .laneRef("stats")
                .downlinkMap()
                .keepSynced(true)
                .open()
                .didUpdate((key, newValue, oldValue) -> {
                    System.out.println("Updated stats (key, value): (" + key.toRecon() + ":" + newValue.toRecon() + ")");
                });
		
	}
}
