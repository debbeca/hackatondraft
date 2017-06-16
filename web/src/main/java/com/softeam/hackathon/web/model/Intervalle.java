package com.softeam.hackathon.web.model;

public enum Intervalle {
	
	JOUR("Jour"), SEMAINE("Semaine"), MOIS("Mois"); 
	
	private String value; 
	
	Intervalle(String val){
		value = val; 
	}
	

	public static Intervalle fromString(String text) {
	    for (Intervalle b : Intervalle.values()) {
	      if (b.value.equalsIgnoreCase(text)) {
	        return b;
	      }
	    }
	    return null;
	  }


}
