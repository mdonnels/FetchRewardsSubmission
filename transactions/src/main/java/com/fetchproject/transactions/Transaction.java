package com.fetchproject.transactions;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "transactions")
public class Transaction implements Comparable<Transaction>{
	
	@Id
	private String id;
	private String payer;
	private Integer points;
	private Date timestamp;
	
	public Transaction(String payer, Integer points, Date timestamp) {
		this.payer = payer;
		this.points = points;
		this.timestamp = timestamp;
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
	public Date getTimeStamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	
	@Override
    public String toString() {
        return "id:" + this.id + ", payer: " + payer //
                + ", points: " + this.points + ", timestamp: " + this.timestamp;
    }

	@Override
	public int compareTo(Transaction transaction) {
		return this.timestamp.compareTo(transaction.timestamp);
	}
	
}
