package it.bio.cases;

public enum AlgorithmCases {
	
	TWO("2", "Explicit extension"), 
	TWO_PLUS("2+", "Implicit extension"),
	THREE("3", "Do nothing");
	
	private final String number;
	private final String label;
	
	private AlgorithmCases(String number, String label) {
		this.number = number;
		this.label = label;
	}	
	
	public String getNumber() {
		return number;
	}
	
	public String getLabel() {
		return label;
	}

	public static AlgorithmCases fromNumber(String number) {
		AlgorithmCases result = null;
		
		for (AlgorithmCases a : AlgorithmCases.values()) {
			if (a.getNumber().equals(number)) {
				result = a;
				break;
			}
		}
		
		return result;
	}
}
