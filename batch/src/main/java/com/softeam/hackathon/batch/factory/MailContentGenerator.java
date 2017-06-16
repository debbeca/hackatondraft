/**
 * 
 */
package com.softeam.hackathon.batch.factory;

import com.softeam.hackathon.batch.factory.bogen.CustomTendanceFactory;
import com.softeam.hackathon.batch.factory.bogen.LastMonthFactory;
import com.softeam.hackathon.batch.factory.transactiongen.TransactionReportFactory;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Hackathon8 s
 */
public class MailContentGenerator {

	private static final TransactionReportFactory TRANSACTION_REPORT_FACTORY = new TransactionReportFactory();
	private static final LastMonthFactory LAST_MONTH_FACTORY = new LastMonthFactory();
	private static final CustomTendanceFactory CUSTOM_TENDANCE_FACTORY = new CustomTendanceFactory();

	public List<String> getTransactionGoogle() {
		return CUSTOM_TENDANCE_FACTORY.getTransactionGoogle().stream()
				.map(item -> TRANSACTION_REPORT_FACTORY.getTransactionTextFormatTable(item))
				.collect(Collectors.toList());
	}

	public List<String> getTransactionFacebook() {
		return CUSTOM_TENDANCE_FACTORY.getTransactionFacebook().stream()
				.map(item -> TRANSACTION_REPORT_FACTORY.getTransactionTextFormatTable(item))
				.collect(Collectors.toList());
	}

	public List<String> getLastMonthByProduct(String productName) {
		return LAST_MONTH_FACTORY.getByProduct(productName).stream()
				.map(item -> TRANSACTION_REPORT_FACTORY.getTransactionTextFormatTable(item))
				.collect(Collectors.toList());
	}

	public List<String> getOneDateByProduct(String productName, int nbTransactions) {
		return LAST_MONTH_FACTORY.getManyTansactionsByProductOneDate(productName, nbTransactions).stream()
				.map(item -> TRANSACTION_REPORT_FACTORY.getTransactionTextFormatTable(item))
				.collect(Collectors.toList());
	}

}
