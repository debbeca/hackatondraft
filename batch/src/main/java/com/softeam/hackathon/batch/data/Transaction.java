package com.softeam.hackathon.batch.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Transaction {


	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long id;


	private String buyer;
	private String seller;
	private String product;
	private Double unitPrice;
	private Integer quantity;
	private Integer fees;
	private Double totalAmount;
	private Date tradeDate;
	private String currency;

	@Override
	public String toString() {
		return "Transaction{" +
				"id=" + id +
				", buyer='" + buyer + '\'' +
				", seller='" + seller + '\'' +
				", product='" + product + '\'' +
				", unitPrice=" + unitPrice +
				", quantity=" + quantity +
				", fees=" + fees +
				", totalAmount=" + totalAmount +
				", tradeDate=" + tradeDate +
				", currency='" + currency + '\'' +
				'}';
	}

	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getBuyer() {
		return buyer;
	}
	public void setBuyer(String buyer) {
		this.buyer = buyer;
	}
	public String getSeller() {
		return seller;
	}
	public void setSeller(String seller) {
		this.seller = seller;
	}
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public Double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Integer getFees() {
		return fees;
	}
	public void setFees(Integer fees) {
		this.fees = fees;
	}
	public Double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public Date getTradeDate() {
		return tradeDate;
	}
	public void setTradeDate(Date tradeDate) {
		this.tradeDate = tradeDate;
	}


}
