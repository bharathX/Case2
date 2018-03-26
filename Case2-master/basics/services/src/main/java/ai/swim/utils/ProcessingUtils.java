package ai.swim.util;

import recon.*;
import swim.api.MapDownlink;
import ai.swim.model.CsvRecord;
import java.util.*;

public class ProcessingUtils {
  public static void genStats(MapDownlink<Integer, CsvRecord> stats, CsvRecord newValue, Integer key) {
    ArrayList<Double> rowData = newValue.getRowValue();

    //System.out.println("Processing row --> " + key);
    double[] data = rowData.stream().mapToDouble(Double::doubleValue).toArray();
     Arrays.sort(data);
					
    //System.out.println("Reading Row Data --> " + data);
    CsvRecord r= new CsvRecord();
    r.setMin(data[0]);
    r.setRowNum(key);
    r.setMax(data[data.length-1]);
					
	double total = Arrays.stream(data).sum();
	r.setMean(total/data.length);
    stats.put(key, r);
  }
}
