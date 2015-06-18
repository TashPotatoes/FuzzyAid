package Controller;

import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import easyfuzzy.controller.BasicFuzzyController;
import easyfuzzy.rules.modifier.FzSet;
import easyfuzzy.variables.IllegalSetException;
import easyfuzzy.variables.LinguisticVariable;
import easyfuzzy.variables.functions.FunctionException;
import easyfuzzy.variables.functions.RectangularMembershipFunction;
import easyfuzzy.variables.functions.TrapezoidalMembershipFunction;
import easyfuzzy.variables.functions.TriangularMembershipFunction;

public class MembershipPage extends Page {
	XYSeriesCollection allFunctions;
	String[] varNames;
	FuzzyType[] varTypes;
	float[][] varPoints;
	
	final int LING_VAR_INDEX = 0;
	final int NAME_INDEX = 1;
	final int TYPE_INDEX = 2;
	final int POINT_INDEX = 3;
	
	public MembershipPage() {
		super();
		
		String newDescription = "something descriptiong balbjreaglketgnjkjltnhyukj stjgh hgklers gjtk;bjs;tr hj";
		String[] newColNames = {"Linguistic Var", "Names", "Types", "Point a", "Point b", "Point c", "Point d"};
		Object[][] newInData = {{"Distance", "TargetClose", FuzzyType.TRAPEZOIDAL, 0, 0, 25, 50},
								{"Distance", "TargetMedium", FuzzyType.TRIANGULAR, 25, 300, 150, null},
								{"Distance", "TargetFar", FuzzyType.TRAPEZOIDAL, 150, 300, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY},
								{"Ammostatus", "Low", FuzzyType.TRIANGULAR, 0, 10, 0, null},
								{"Ammostatus", "Okay", FuzzyType.TRIANGULAR, 0, 30, 10, null},
								{"Ammostatus", "Loads", FuzzyType.TRAPEZOIDAL, 10, 30, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY}								
				};
		
		//set outData later
				
		this.setShowSurface(true);
		this.setVisualTitle("Fuzzy Membership Functions");
		this.setXTitle("Input");
		this.setYTitle("Truth Degree");
		this.setDescription(newDescription);
		this.setColNames(newColNames);
		this.setInData(newInData);
		varPoints = new float[50][50];
		
		System.out.println("new data assigned");
		// TODO Auto-generated constructor stub
		

		

	
	
	}
	
	public BasicFuzzyController createFuzzyVariables(Object[][] data, BasicFuzzyController bfc){
		try {
			double a;
			double b;
			double c;
			double d;
			String newLingVar; 
			int i = 0;
			
			while (i < data.length){
				// add the linguistic variable
				newLingVar = (String) data[i][LING_VAR_INDEX];
				LinguisticVariable lv = new LinguisticVariable(newLingVar); //TODO check not deleting and that bfc is being modified.

				// add the associated fuzzy sets by adding the first 
				// one and then iteratively adding the rest until the 
				// next set no longer belongs to the current linquistic variable 
				do {
					a = (double) data[i][POINT_INDEX];
					b = (double) data[i][POINT_INDEX+1];
					c = (double) data[i][POINT_INDEX+2];
					d = (double) data[i][POINT_INDEX+3];

					switch ((FuzzyType) this.getInData()[i][TYPE_INDEX]){
					case TRAPEZOIDAL:
						lv.addSet((String) data[i][NAME_INDEX], new TrapezoidalMembershipFunction(a, b, c, d));					
						break;						
					case TRIANGULAR:
						lv.addSet((String) data[i][NAME_INDEX], new TriangularMembershipFunction(a, b, c));					
						break;					
					case SQUARE:
						lv.addSet((String) data[i][NAME_INDEX], new RectangularMembershipFunction(a, b, c));					
						break;							
					default:
						break;						
					}
					i++;
				} while((i < data.length) && (newLingVar == data[i][TYPE_INDEX]));

				// after the linguistic variable and all it's fuzzy sets have been added, begin adding the next linguistic variable
				bfc.addVariable(lv);
				i++;
			}
	
	        System.out.println("input VARIABLES CREATED!");
		} catch (IllegalSetException ex) {
            ex.printStackTrace();
        } catch (FunctionException ex){
        	ex.printStackTrace();
        }
		
		return bfc;
		
	}

	@Override
	public boolean sanityCheckData() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void parseInData(Object[][] data) {
		// should be of the form string for varName, fuzzyType for varType, 
		for (int i = 0; i < data.length; i++){
			//varNames[i] = (String) data[i][NAME_INDEX];
			//varTypes[i] = (FuzzyType) data[i][TYPE_INDEX];
			for (int j = POINT_INDEX; j < data[0].length; j++){
				System.out.println("stuff about to add to varpoints i" + i +" j" + j+ " " +(int) data[i][j]);
				if (data[i][j] != null){
					varPoints[i][j] = (int) data[i][j];	
				}				
			}			
		}
		this.setOutChartData(varPoints);
	}

	@Override //called higher up
	public void calculateVisual() {
		// TODO Auto-generated method stub
		parseInData(this.getInData());
		//createFuzzyVariables(this.getInData(), this.bfc);
		createDataSeries(varPoints);
	}
//kill kill kill?
	
	@Override
	public void drawVisual() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public XYSeriesCollection createDataSeries(float[][] data){
		
		allFunctions = new XYSeriesCollection();
		
		//  i is one fuzzy membership?
		// or is i function type or variable?
		for (int i = 0; i < data.length; i++){
			float a = data[i][1];
			float b = data[i][2];
			float c = data[i][3];
			float d = data[i][4];
			
			
			if (data[i][0] == 0) {
				// Rectangular membership goes (a,0) to (a, c) to (b,c) to (b,0)
				int colTitleIndex = (int) data[i][0];
				//this.getColNames()[];
				XYSeries function = new XYSeries(varNames[i] + " " + varTypes[i]);
				function.add(a, 0);
				function.add(a, c);
				function.add(b, c);
				function.add(b, 0);	
				allFunctions.addSeries(function);
			
			} else if (data[i][0] == 1) {
				// Trapezoidal membership goes (a,0) to (b,1) to (c,1) to (d,0)
				XYSeries function = new XYSeries(varNames[i] + " " + varTypes[i]);	
				function.add(a, 0);
				function.add(b, 1);
				function.add(c, 1);
				function.add(d, 0);								
				allFunctions.addSeries(function);
				
			} else if (data[i][0] == 2) {
				// Triangle membership goes (a,0) to (c,1) to (b,0)
				XYSeries function = new XYSeries(varNames[i] + " " + varTypes[i]);
				function.add(a, 0);
				function.add(c, 1);
				function.add(b, 0);
				allFunctions.addSeries(function);
				
			}
			
		}
		return allFunctions;
	}

	
	@Override
	public BasicFuzzyController runLogic(BasicFuzzyController bfc) {
		// TODO Auto-generated method stub
		return createFuzzyVariables(this.getInData(), bfc);
	}
	
	


}
