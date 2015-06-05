package Controller;
import java.util.Vector;


public abstract class Page {
	private String description;
	private String[] colNames;
	private Object[][] inData;
	private Object[][] outData;
	
	public Page(String description, String[]colNames, Object[][] inData, Object[][] outData){
		this.description = description;
		this.colNames = colNames;
		this.inData = inData;
		this.outData = outData;		
	}
	
	public String getDescription(){
		return description;
	}
	public void setDescription(String newDescription){
		this.description = newDescription;
	}
	
	public String[] getColNames(){
		return colNames;
	}
	
	public void setColName(String[] newColNames){
		this.colNames = newColNames;
	}
	
	public Object[][] getInData(){
		return inData;
	}
	
	public void setInData(Object[][] newInData){
		this.inData = newInData;
	}
	
	public void setInDataAt(int row, int col, String val){
		this.inData[row][col] = val;
	}
	
	public Object[][] getOutData(){
		return outData;
	}
	
	public Object getOutDataAt(int row, int col){
		return outData[row][col];
	}
	
	public void setOutData(Object[][] newOutData){
		this.outData = newOutData;
	}
	
	public void setOutDataAt(int row, int col, Object val){
		this.outData[row][col] = val;
	}
	
	abstract public boolean sanityCheckData();
	abstract public void parseInData();
	abstract public void drawVisual();
	abstract public void calculateVisual();
		
}
