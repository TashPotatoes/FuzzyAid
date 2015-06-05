
public enum FuzzyVar { 
	TRIANGULAR(0,"Triangle"), SQUARE(1, "Square"), TRAPEZOIDAL(2, "Trapezoid");
	
//	private String[] Types = {"Trianlge", "Square", "Trapezoid"};
	
	
	private int valueInt;
	private String valueString;
	
	private FuzzyVar(int valueInt, String valueString) {
        this.valueInt = valueInt;
        this.valueString = valueString;
	}
}



