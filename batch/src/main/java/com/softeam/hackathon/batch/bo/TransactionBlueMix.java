/**
 * 
 */
package com.softeam.hackathon.batch.bo;

/**
 * @author Hackathon8
 *
 */
public class TransactionBlueMix {

	private String productName;
	private String productType;
	private String productISIN;

	private long id;

	private String buyer;
	private String seller;
	private String product;
	private Double unitPrice;
	private Integer quantity;
	private Integer fees;
	private Double totalAmount;
	private String tradeDate;
	private String currency;

	@Override
	public String toString() {
		return "TransactionBlueMix [productName=" + productName + ", productType=" + productType + ", productISIN="
				+ productISIN + ", id=" + id + ", buyer=" + buyer + ", seller=" + seller + ", product=" + product
				+ ", unitPrice=" + unitPrice + ", quantity=" + quantity + ", fees=" + fees + ", totalAmount="
				+ totalAmount + ", tradeDate=" + tradeDate + ", currency=" + currency + "]";
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getProductISIN() {
		return productISIN;
	}

	public void setProductISIN(String productISIN) {
		this.productISIN = productISIN;
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

	public String getTradeDate() {
		return tradeDate;
	}

	public void setTradeDate(String tradeDate) {
		this.tradeDate = tradeDate;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

}
