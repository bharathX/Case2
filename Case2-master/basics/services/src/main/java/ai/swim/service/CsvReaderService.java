package ai.swim.service;

import ai.swim.model.CsvRecord;
import ai.swim.model.CsvRowData;
import swim.api.*;
import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import recon.Value;
import swim.util.Uri;
import ai.swim.util.ProcessingUtils;

public class CsvReaderService extends AbstractService {

	private MapDownlink<Integer, CsvRecord> statsLink;

		/**
	 * Use a value lane to store a single data item, the class type of the item needs to be specified
	 * In this case store value lane is of type Integer
	 *
	 * didUpdate is called when the MapLane gets updated with a new value
	 */
	@SwimLane("latest")
	MapLane<Integer, CsvRecord> latest = mapLane().keyClass(Integer.class).valueClass(CsvRecord.class)
			.didUpdate((i, newValue, oldValue) -> {
				//System.out.println("latest lane " + i + " set with value: " + newValue );
				ProcessingUtils.genStats(statsLink, newValue, i);

			});


	/**
	 * Use a command lane to ingest data from an external client, the class type of the data item needs to be specified
	 * In this case the command lane is of type String
	 */
	@SwimLane("addLatest")
	CommandLane<String> addLatest = commandLane().valueClass(String.class)
			.onCommand(filePath -> {
				
				String line = "";
				String cvsSplitBy = ",";
				int row = 0;
				System.out.println("Reading csv file ");
				try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

					while ((line = br.readLine()) != null) {

						System.out.println("Reading row --> " + row);
						// use comma as separator
						String[] numberStrs = line.split(cvsSplitBy);

						ArrayList<Double> doubles = new ArrayList<Double>();
						for(int i = 0;i < numberStrs.length;i++)
						{
							doubles.add(Double.parseDouble(numberStrs[i]));
						}
						
						CsvRecord csvRecord= new CsvRecord();
						csvRecord.setRowNum(row);
						csvRecord.setRowValue(doubles);
						latest.put(row,csvRecord);
						
						row++;
					}

				} catch (IOException e) {
					e.printStackTrace();
				}
		       		

			});

	@SwimLane("stats")
	private MapLane<Integer, CsvRecord> stats = mapLane()
			.keyClass(Integer.class) // we don't need to specify .valueClass(Value.class) because Value is the default
			.valueClass(CsvRecord.class)
			.didUpdate((k,n,o) -> {
				//System.out.println("[stats] k: " + k + "; n: " + n);
			});


	@Override
	public void didStart() {
		// creating a MapDownlink for the stats lane in order to reuse code in ProcessingUtils
		statsLink = context
				.hostRef(hostUri())
				.nodeRef(nodeUri())
				.laneRef("stats")
				.downlinkMap()
				.keepSynced(true)
				.keyClass(Integer.class) // this is required before open since the key is not of type Value
				.valueClass(CsvRecord.class)
				.open();
	}
}
