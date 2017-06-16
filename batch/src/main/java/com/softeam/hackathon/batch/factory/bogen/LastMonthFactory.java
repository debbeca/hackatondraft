/**
 * 
 */
package com.softeam.hackathon.batch.factory.bogen;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;

import com.softeam.hackathon.batch.bo.TransactionBlueMix;
import com.softeam.hackathon.batch.utils.DateUtils;

/**
 * @author Hackathon8
 *
 */
public class LastMonthFactory {

	private static final int MAX_QUANTITY = 20000;
	private String[] buyers = new String[] {"Natixis" , "BNP"};
	private String[] sellers = new String[] {"Natixis" , "BNP"};
	private String[] prodcuts = new String[] {"P1","P2"};

	public LastMonthFactory() {

	}

	protected String getAnyDateLastMonth() {
		Integer jourDuMois = new Random().nextInt(29) + 1;
		return jourDuMois < 19 ? StringUtils.leftPad(jourDuMois.toString(), 2, "0") + "/06/2017"
				: StringUtils.leftPad(jourDuMois.toString(), 2, "0") + "/05/2017";
	}

	public static String getMailTradeDate(String dateStr) {
		LocalDate localdate = LocalDate.parse(dateStr, DateUtils.LOCAL_DATE_FORMAT);
		String maildate = StringUtils.trim(StringUtils.split(
				localdate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).withLocale(Locale.UK)), ",")[1]);
		String[] parts = StringUtils.split(maildate, " ", 2);
		String mailDateFinal = parts[0] + DateUtils.suffixes[Integer.valueOf(parts[0])] + " " + parts[1];
		return mailDateFinal;
	}

	protected String getAnyMailTradeDateLastMonth() {
		return getMailTradeDate(getAnyDateLastMonth());
	}

	public List<TransactionBlueMix> getByProduct(String product) {
		List<TransactionBlueMix> result = new ArrayList<>();
		for (Integer index = 1; index <= 19; index++) {
			TransactionBlueMix transaction = getByDate(product,
					StringUtils.leftPad(index.toString(), 2, "0") + "/06/2017");
			result.add(transaction);
		}
		for (Integer index = 18; index <= 30; index++) {
			TransactionBlueMix transaction = getByDate(product,
					StringUtils.leftPad(index.toString(), 2, "0") + "/05/2017");
			result.add(transaction);
		}
		return result;
	}

	public List<TransactionBlueMix> getManyTansactionsByProductOneDate(String product, int nbTransactions) {
		List<TransactionBlueMix> result = new ArrayList<>();
		String date = getAnyDateLastMonth();
		for (int index = 0; index <= nbTransactions; index++) {
			TransactionBlueMix transaction = getByDate(product, date);
			result.add(transaction);
		}
		return result;
	}

	private TransactionBlueMix getByDate(String product, String dateStr) {
		TransactionBlueMix transaction = new TransactionBlueMix();
		transaction.setProductISIN("FR000LMBP" + product.toUpperCase().substring(0, 2));
		transaction.setProductName(product);
		transaction.setProductType("Share");
		transaction.setCurrency("EUR");
		transaction.setUnitPrice(new Double(new Random().nextInt(100)));
		transaction.setBuyer("NATIXIS");
		transaction.setSeller("BNP");
		transaction.setQuantity(new Random().nextInt(20000));
		transaction.setFees(new Random().nextInt(200));
		transaction.setTotalAmount(transaction.getFees() + transaction.getUnitPrice() * transaction.getQuantity());
		transaction.setTradeDate(getMailTradeDate(dateStr));

		return transaction;
	}

	public TransactionBlueMix buildTransactionBlueMix(String buyer, String seller, Integer fees, String productName,
			Integer quantity, Double unitPrice) {
		return buildTransactionBlueMix(buyer, null, fees, null, productName, quantity, seller, null, unitPrice, null);
	}

	public TransactionBlueMix buildTransactionBlueMix(String buyer, String seller, Integer fees, String productName,
			Integer quantity, Double unitPrice, String tradeDate) {
		return buildTransactionBlueMix(buyer, null, fees, null, productName, quantity, seller, null, unitPrice,
				tradeDate);
	}

	public TransactionBlueMix buildTransactionBlueMix(String buyer, String currency, Integer fees, String productISIN,
			String productName, Integer quantity, String seller, Double totalAmount, Double unitPrice,
			String tradeDate) {
		if (buyer == null) {
			buyer = getRandomBuyer(seller);
		}
		if (seller == null) {
			seller = getRandomSeller(buyer);
		}
		if (currency == null) {
			currency = "EUR";
		}
		if (fees == null) {
			fees = new Random().nextInt();
		}
		if (productName == null) {
			productName = getRandomProduct();
		}
		if (productISIN == null) {
			productISIN = "FR000LMBP" + productName.toUpperCase().substring(0, 2);
		}
		if (quantity == null) {
			quantity = new Random().nextInt(MAX_QUANTITY);
		}
		if (unitPrice == null) {
			unitPrice = new Double(new Random().nextInt(100));
		}
		if (totalAmount == null) {
			totalAmount = fees + unitPrice * quantity;
		}
		if (tradeDate == null) {
			tradeDate = getAnyMailTradeDateLastMonth();
		}
		return new TransactionBlueMixBuilder().setBuyer(buyer).setCurrency(currency).setFees(fees)
				.setProductISIN(productISIN).setProductName(productName).setQuantity(quantity).setSeller(seller)
				.setTotalAmount(totalAmount).setUnitPrice(unitPrice).setTradeDate(tradeDate).get();
	}

	private String getRandomProduct() {
		int index = new Random().nextInt(prodcuts.length);
		return prodcuts[index];
	}

	private String getRandomBuyer(String seller) {

		String buyer = getRandomBuyer();
		while (seller != null && buyer.equals(seller)) {
			buyer = getRandomBuyer();
		}
		return buyer;
	}

	private String getRandomBuyer() {
		int index = new Random().nextInt(buyers.length);
		return buyers[index];
	}

	private String getRandomSeller(String buyer) {

		String seller = getRandomSeller();
		while (buyer != null && buyer.equals(seller)) {
			seller = getRandomSeller();
		}
		return seller;
	}

	private String getRandomSeller() {
		int index = new Random().nextInt(sellers.length);
		return sellers[index];
	}
	

}
