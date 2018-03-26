package ai.swim.model;

import recon.Form;
import recon.ReconName;
import recon.Value;
import java.util.*;
import java.util.Objects;

/**
 * Use this pattern to convert a POJO to a Recon Value
 * The value in the @ReconName annotation associated with the class name will be used as the header of the Recon
 * representation for all objects of type ModelB
 *
 * The value in the @ReconName annotation associated with a field will be used in the Recon representation of that field
 *
 * Eg: Consider a ModelB object has the following fields: {bool=false, str="1", intg=1, lng=1L, flt=1.0, dbl=1.0
 *     It's recon representation will be: @modelB{b:false,s:"1",i:1,l:1,f:1,d:1}
 *
 * Refer to the toValue method to get the Recon Value for a POJO instance
 */

@ReconName("CsvRecord")
public class CsvRecord {


	public CsvRecord() {
	}

	@ReconName("rowNum")
	private int rowNum;

	@ReconName("rowValue")
	private ArrayList<Double> rowValue;

	@ReconName("min")
	private double min ;

	@ReconName("max")
	private double max;

	@ReconName("mean")
	private double mean;
	//private long rowNum;

	public ArrayList<Double> getRowValue() {
		return rowValue;
	}
	//public double getColumnValue (){

		//return columnValue;
	//}
	public void setRowValue(ArrayList<Double> rowValue)   {
		this.rowValue = rowValue;
	}

	public int getRowNum() {
		return rowNum;
	}

	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
	}

	public double getMin() {
		return min;
	}

	public void setMin(double min) {
		this.min = min;
	}

	public double getMax() {
		return max;
	}

	public void setMax(double max) {
		this.max = max;
	}

	public double getMean() {
		return mean;
	}

	public void setMean(double mean) {
		this.mean = mean;
	}

	/*public long getRowNum() {
		return rowNum;
	}*/

	@Override
	public String toString() {
		return "Record [Min=" + min + ", Max=" + max + ", Mean=" + mean + "]";
	}


}
