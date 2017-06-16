/**
 *
 */
package com.softeam.hackathon.batch.factory.transactiongen;

import com.softeam.hackathon.batch.bo.TransactionBlueMix;
import com.softeam.hackathon.batch.utils.DateUtils;

/**
 * @author Hackathon8
 */
public class TransactionReportFactory {

	public TransactionReportFactory() {

	}

	public String getTransactionTextFormatTable(TransactionBlueMix inputTransaction) {
		StringBuilder builder = new StringBuilder();
		builder.append("Trade Confirmation").append("\n").append("\n");
		builder.append("Dear Client,").append("\n");
		builder.append("The purpose of this letter (this \"Confirmation\") is to confirm the terms and").append("\n");
		builder.append("conditions of the Transaction entered into between us on the Trade Date").append("\n");
		builder.append("specified below (the \"Transaction\").").append("\n").append("\n");
//		builder.append("Type : ").append(inputTransaction.getProductType()).append("\n");
		builder.append("Trade Date : ").append(inputTransaction.getTradeDate()).append("\n");
		builder.append("Name : ").append(inputTransaction.getProductName()).append("\n");
		builder.append("ISIN : ").append(inputTransaction.getProductISIN()).append("\n");
		builder.append("Buyer : ").append(inputTransaction.getBuyer()).append("\n");
		builder.append("Seller : ").append(inputTransaction.getSeller()).append("\n");
		builder.append("Quantity : ").append(inputTransaction.getQuantity()).append("\n");
		builder.append("Unit Price : ").append(inputTransaction.getCurrency()).append(" ")
				.append(inputTransaction.getUnitPrice()).append("\n");
		builder.append("Commission : ").append(inputTransaction.getCurrency()).append(" ")
				.append(inputTransaction.getFees()).append("\n");
		builder.append("Total Transaction Amount : ").append(inputTransaction.getCurrency()).append(" ")
				.append(inputTransaction.getTotalAmount()).append("\n");
		return builder.toString();
	}

	public String getTransactionTextFormatText(TransactionBlueMix inputTransaction) {
		StringBuilder builder = new StringBuilder();
		// builder.append("Confirmation of Equity
		// Transaction").append("\n").append("\n");
		builder.append("[Letterhead of ").append(inputTransaction.getSeller()).append("\n");
		builder.append("June 9th 2017").append("\n");
		// builder.append("Equity Transaction").append("\n").append("\n");
		builder.append("[").append(inputTransaction.getBuyer()).append("]").append("\n").append("\n");
		builder.append("Dear Sirs:").append("\n").append("\n");
		builder.append("I wish to confirm the terms and conditions of the Transaction between ");
		builder.append(inputTransaction.getSeller()).append(" and ").append(inputTransaction.getBuyer());
		builder.append(" on the effective date. ");
		builder.append("On the execution date of ").append(inputTransaction.getTradeDate()).append(", ");
		builder.append(inputTransaction.getSeller()).append(" sells ").append(inputTransaction.getQuantity())
				.append(" of ");
		builder.append(inputTransaction.getProductType()).append(" of the ").append(inputTransaction.getProductName());
		builder.append(" ( ISIN ").append(inputTransaction.getProductISIN()).append(" ) ");
		builder.append(" at a price of ").append(inputTransaction.getCurrency()).append(" ")
				.append(inputTransaction.getUnitPrice());
		builder.append(". Commission is ").append(inputTransaction.getCurrency()).append(" ")
				.append(inputTransaction.getFees()).append("\n");
		builder.append("The deal will represent a total payment of ").append(inputTransaction.getCurrency()).append(" ")
				.append(inputTransaction.getTotalAmount()).append("\n");
		return builder.toString();
	}

}
