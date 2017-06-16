package com.softeam.hackathon.web.model;

import java.util.Date;


public class Tendancy {

    private Date x;

    private Double y;

    public Tendancy(Object o1, Object o2) {
        x = (Date) o1;
        y = (Double) o2;
    }

	public Date getX() {
		return x;
	}

	public void setX(Date x) {
		this.x = x;
	}

	public Double getY() {
		return y;
	}

	public void setY(Double y) {
		this.y = y;
	}

  
}
