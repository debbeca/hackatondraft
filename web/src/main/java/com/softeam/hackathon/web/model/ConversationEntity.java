package com.softeam.hackathon.web.model;

public enum ConversationEntity {
	
	INTERVALLE("Intervalle"), APPORTEUR("Apporteur");
	
	
	private String value;
	
	ConversationEntity(String val){
		value = val; 
	}
	
	public static ConversationEntity fromString(String text) {
	    for (ConversationEntity b : ConversationEntity.values()) {
	      if (b.value.equalsIgnoreCase(text)) {
	        return b;
	      }
	    }
	    return null;
	  }

}
