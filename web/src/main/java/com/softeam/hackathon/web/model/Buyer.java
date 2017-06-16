package com.softeam.hackathon.web.model;

public class Buyer {

    private String label;

    private Double y;

    public Buyer(Object o1, Object o2) {
    	label= (String) o1;
        y = (Double) o2;
    }

	public String getLabel() {
		return label;
	}

	public void setLabel(String x) {
		this.label = x;
	}

	public Double getY() {
		return y;
	}

	public void setY(Double y) {
		this.y = y;
	}
    
    

}
