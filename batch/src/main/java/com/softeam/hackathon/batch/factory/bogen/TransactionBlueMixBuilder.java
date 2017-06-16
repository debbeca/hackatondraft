/**
 * 
 */
package com.softeam.hackathon.batch.factory.bogen;

import com.softeam.hackathon.batch.bo.TransactionBlueMix;

/**
 * @author Hackathon8
 *
 */
public class TransactionBlueMixBuilder {

	TransactionBlueMix transaction;

	public TransactionBlueMixBuilder() {
		this.transaction = new TransactionBlueMix();
	}
	
	public TransactionBlueMix get() {
		return transaction ;
	}

	public TransactionBlueMixBuilder setProductName(String productName) {
		transaction.setProductName(productName);
		return this;
	}

	public TransactionBlueMixBuilder setProductType(String productType) {
		transaction.setProductType(productType);
		return this;
	}

	public TransactionBlueMixBuilder setProductISIN(String productISIN) {
		transaction.setProductISIN(productISIN);
		return this;
	}

	public TransactionBlueMixBuilder setBuyer(String buyer) {
		transaction.setBuyer(buyer);
		return this;
	}

	public TransactionBlueMixBuilder setSeller(String seller) {
		transaction.setSeller(seller);
		return this;
	}

	public TransactionBlueMixBuilder setProduct(String product) {
		transaction.setProduct(product);
		return this;
	}

	public TransactionBlueMixBuilder setUnitPrice(Double unitPrice) {
		transaction.setUnitPrice(unitPrice);
		return this;
	}

	public TransactionBlueMixBuilder setQuantity(Integer quantity) {
		transaction.setQuantity(quantity);
		return this;
	}

	public TransactionBlueMixBuilder setFees(Integer fees) {
		transaction.setFees(fees);
		return this;
	}

	public TransactionBlueMixBuilder setTotalAmount(Double totalAmount) {
		transaction.setTotalAmount(totalAmount);
		return this;
	}

	public TransactionBlueMixBuilder setTradeDate(String tradeDate) {
		transaction.setTradeDate(tradeDate);
		return this;
	}

	public TransactionBlueMixBuilder setCurrency(String currency) {
		transaction.setCurrency(currency);
		return this;
	}

}
