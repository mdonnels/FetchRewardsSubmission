package com.fetchproject.transactions;


public class PointDeduction {
	public String payer;
	public Integer points;
	
	public PointDeduction(String payer, Integer points) {
		this.payer = payer;
		this.points = points;
	}
	
	public String getPayer() {
		return payer;
	}
	public void setPayer(String payer) {
		this.payer = payer;
	}
	public Integer getPoints() {
		return points;
	}
	public void setPoints(Integer points) {
		this.points = points;
	}

}
