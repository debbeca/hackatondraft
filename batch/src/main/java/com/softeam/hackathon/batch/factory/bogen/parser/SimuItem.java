/**
 * 
 */
package com.softeam.hackathon.batch.factory.bogen.parser;

/**
 * @author Hackathon8
 *
 */
public class SimuItem {

	private String date;
	private Integer nbTransaction;
	private Integer quantity;
	private Double unitPrice;

	public SimuItem() {
		super();
	}

	public SimuItem(String date, String nbTransaction, String quantity, String unitPrice) {
		this.date = date;
		this.nbTransaction = Integer.valueOf(nbTransaction);
		this.quantity = Integer.valueOf(quantity);
		this.unitPrice = Double.valueOf(unitPrice);
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getNbTransaction() {
		return nbTransaction;
	}

	public void setNbTransaction(int nbTransaction) {
		this.nbTransaction = nbTransaction;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

}
