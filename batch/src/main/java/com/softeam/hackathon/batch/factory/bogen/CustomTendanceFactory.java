/**
 * 
 */
package com.softeam.hackathon.batch.factory.bogen;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.softeam.hackathon.batch.bo.TransactionBlueMix;
import com.softeam.hackathon.batch.factory.bogen.parser.CSVReader;
import com.softeam.hackathon.batch.factory.bogen.parser.SimuItem;

/**
 * @author Hackathon8
 *
 */
public class CustomTendanceFactory {

	private LastMonthFactory lastMonthFactory = new LastMonthFactory();

	public List<TransactionBlueMix> getTransaction(List<SimuItem> items, String prodcutName) {
		List<TransactionBlueMix> result = new ArrayList<>();
		for (SimuItem item : items) {
			for (int i = 0; i < item.getNbTransaction(); i++) {
				result.add(lastMonthFactory.buildTransactionBlueMix(null, null, null, "GOOGLE", item.getQuantity(),
						item.getUnitPrice(), LastMonthFactory.getMailTradeDate(item.getDate())));
			}
		}
		return result;
	}

	private List<SimuItem> getItems(Path itemFilePath) {
		CSVReader cvsReader = new CSVReader();
		List<SimuItem> items = cvsReader.getItems(itemFilePath);
		return items;
	}

	public List<TransactionBlueMix> getTransactionGoogle() {
		List<SimuItem> items = getItems(Paths.get("C:\\inputDeals\\DataGoogle.txt"));
		return getTransaction(items, "GoogleAction");
	}

	public List<TransactionBlueMix> getTransactionFacebook() {
		List<SimuItem> items = getItems(Paths.get("C:\\inputDeals\\DataFacebook.txt"));
		return getTransaction(items, "FacebookAction");
	}
}
