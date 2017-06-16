package com.softeam.hackathon.web.model;

public enum Intention {
	
	BEST("Best"), TENDENCY("Tendency"), LAST("Last"), CONSEIL("Conseil"); 
	
	private String value;
	
	Intention(String val){
		value = val; 
	}
	
	public static Intention fromString(String text) {
	    for (Intention b : Intention.values()) {
	      if (b.value.equalsIgnoreCase(text)) {
	        return b;
	      }
	    }
	    return null;
	  }

}
