package Controller;
import java.util.Vector;

import org.jfree.data.xy.XYSeriesCollection;

import easyfuzzy.controller.BasicFuzzyController;


public abstract class Page {
	private boolean showSurface = true;
	private String visualTitle; 
	private String xAxisTitle;
	private String yAxisTitle;
	
	private String description;
	private String[] colNames;
	private Object[][] inData;
	private Object[][] outSurfaceData; //[n][3]
	private float[][] outChartData; // [n][2]
	
	
	public Page(){
		/*this.showSurface = showSurface;
		this.description = description;
		this.colNames = colNames;
		this.inData = inData;
		if (showSurface){
			this.outSurfaceData = outData;
		} else {
			for (int i = 0; i < outData.length; i++){
				for (int j = 0; j < 2; j++)
				this.outChartData[i][j] = (float) outData[i][j];
			}
		}*/		
	}
	
	public void setVisualTitle(String newTitle){
		this.visualTitle = newTitle;
	}
	
	public String getVisualTitle(){
		return this.visualTitle;
	}
	
	public void setXTitle(String newTitle){
		this.xAxisTitle = newTitle;
	}
	
	public String getXTitle(){
		return this.xAxisTitle;
	}
	public void setYTitle(String newTitle){
		this.xAxisTitle = newTitle;
	}
	
	public String getYTitle(){
		return this.yAxisTitle;
	}
	
	public void setShowSurface(boolean state){
		this.showSurface = state;
	}
	
	public boolean getShowSurface(){
		return showSurface;
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
	
	public void setColNames(String[] newColNames){
		this.colNames = newColNames;
	}
	
	public Object[][] getInData(){
		return inData;
	}
	
	public void setInData(Object[][] newInData){
		this.inData = newInData;
	}
	
	public void setInDataAt(int row, int col, Object val){
		this.inData[row][col] = val;
	}
	
	public Object[][] getOutData(){
		return outSurfaceData;
	}
	
	public Object getOutDataAt(int row, int col){
		return outSurfaceData[row][col];
	}
	
	public void setOutData(Object[][] newOutData){
		this.outSurfaceData = newOutData;
	}
	
	public void setOutDataAt(int row, int col, Object val){
		this.outSurfaceData[row][col] = val;
	}
	
	public float[][] getOutChartData(){
		return outChartData;
	}
	
	public Object getOutChartDataAt(int index){
		return outChartData[index];
	}
	
	public void setOutChartData(float[][] newOutData){
		this.outChartData = newOutData;
	}
	
	public void setOutChartDataAt(int row, int col, float val){
		this.outChartData[row][col] = val;
	}
	
	abstract public boolean sanityCheckData();
	abstract public void parseInData(Object[][] data);
	abstract public void drawVisual();
	abstract public void calculateVisual();
	abstract public XYSeriesCollection createDataSeries(float[][] data);
	abstract public BasicFuzzyController runLogic(BasicFuzzyController bfc);
}
