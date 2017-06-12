package com.example.hackaton.data;

import static javax.persistence.GenerationType.AUTO;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Transaction {
	
	
	@Id
    @GeneratedValue(strategy = AUTO)
	private String id;
	
	private String broker; 
	private String stoke; 
	private Double amount;
	private Date transactionDate; 
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBroker() {
		return broker;
	}
	public void setBroker(String broker) {
		this.broker = broker;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public String getStoke() {
		return stoke;
	}
	public void setStoke(String stoke) {
		this.stoke = stoke;
	}
	public Date getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	} 
	 
	
	
}
