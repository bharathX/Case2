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

@ReconName("CsvRowData")
public class CsvRowData {

  
  public CsvRowData() {
  }
  
  @ReconName("data")
  private Map<Integer, ArrayList<Double>> data ;
	


	public Map<Integer, ArrayList<Double>> getData() {
		return data;
	}

	public void setData(Map<Integer, ArrayList<Double>> data) {
		this.data = data;
	}





	/*public long getRowNum() {
		return rowNum;
	}*/

	/*@Override
	public String toString() {
		return "Record [Min=" + min + ", Max=" + max + ", Mean=" + mean + "]";
	}*/

  
}
