/**
 * 
 */
package com.softeam.hackathon.batch.factory.bogen.parser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Hackathon8
 *
 */
public class CSVReader {

	public List<SimuItem> getItems() {
		List<SimuItem> result = new ArrayList<>();

		result.add(new SimuItem("01/06/2017", "2", "10", "5"));
		result.add(new SimuItem("01/06/2017", "2", "10", "5"));
		result.add(new SimuItem("02/06/2017", "2", "10", "5"));
		result.add(new SimuItem("03/06/2017", "2", "10", "5"));
		result.add(new SimuItem("04/06/2017", "3", "10", "5"));
		result.add(new SimuItem("05/06/2017", "3", "10", "5,5"));
		result.add(new SimuItem("06/06/2017", "4", "10", "6"));
		result.add(new SimuItem("07/06/2017", "5", "12", "6,5"));
		result.add(new SimuItem("08/06/2017", "7", "12", "7"));
		result.add(new SimuItem("09/06/2017", "9", "15", "8"));
		result.add(new SimuItem("10/06/2017", "13", "15", "9"));
		result.add(new SimuItem("11/06/2017", "16", "20", "10"));
		result.add(new SimuItem("12/06/2017", "20", "20", "11"));
		result.add(new SimuItem("13/06/2017", "24", "20", "12"));
		result.add(new SimuItem("14/06/2017", "28", "20", "13"));
		result.add(new SimuItem("15/06/2017", "30", "30", "15"));
		result.add(new SimuItem("16/06/2017", "32", "30", "16"));
		result.add(new SimuItem("17/06/2017", "38", "30", "18"));
		result.add(new SimuItem("18/06/2017", "40", "40", "20"));
		result.add(new SimuItem("19/06/2017", "45", "40", "23"));
		result.add(new SimuItem("20/06/2017", "50", "40", "26"));
		return result;
	}

	public List<SimuItem> getItems(Path path) {

		List<SimuItem> result = new ArrayList<>();

		String csvFile = path.toString();
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = "\t";

		try {

			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {
				if (line.startsWith("#")) {
					continue;
				}

				// use comma as separator
				String[] elements = line.split(cvsSplitBy);
				SimuItem item = new SimuItem();
				item.setDate(elements[0]);
				item.setNbTransaction(Integer.parseInt(elements[1]));
				item.setQuantity(Integer.parseInt(elements[2]));
				item.setUnitPrice(Double.parseDouble(elements[3].replaceAll(",", ".")));
				result.add(item);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}

}
