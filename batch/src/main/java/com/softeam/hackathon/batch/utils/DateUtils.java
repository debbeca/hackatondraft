/**
 * 
 */
package com.softeam.hackathon.batch.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.format.annotation.DateTimeFormat;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * @author Hackathon8
 *
 */
public class DateUtils {
	
	
	public static final DateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

	public static final DateTimeFormatter LOCAL_DATE_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	public static final DateTimeFormatter MAIL_DATE_FORMAT = DateTimeFormatter.ofPattern("dd MMM yyyy").withLocale(Locale.UK);

	public static String[] suffixes =
			//    0     1     2     3     4     5     6     7     8     9
			{ "th", "st", "nd", "rd", "th", "th", "th", "th", "th", "th",
					//    10    11    12    13    14    15    16    17    18    19
					"th", "th", "th", "th", "th", "th", "th", "th", "th", "th",
					//    20    21    22    23    24    25    26    27    28    29
					"th", "st", "nd", "rd", "th", "th", "th", "th", "th", "th",
					//    30    31
					"th", "st" };

	public static String replaceMonthPartToFull(String partDate) {
		String result = partDate;

		result = StringUtils.replace(result, "Jan", "January");
		result = StringUtils.replace(result, "Feb", "February");
		result = StringUtils.replace(result, "Mar", "March");
		result = StringUtils.replace(result, "Apr", "April");
		result = StringUtils.replace(result, "Jun", "June");
		result = StringUtils.replace(result, "Jul", "July");
		result = StringUtils.replace(result, "Aug", "August");
		result = StringUtils.replace(result, "Sep", "September");
		result = StringUtils.replace(result, "Oct", "October");
		result = StringUtils.replace(result, "Nov", "November");
		result = StringUtils.replace(result, "Dec", "December");

		return result;
	}

	public static String replaceMonthFullToPart(String partDate) {
		String result = partDate;

		result = StringUtils.replace(result, "January"  , "Jan");
		result = StringUtils.replace(result, "February" , "Feb");
		result = StringUtils.replace(result, "March"    , "Mar");
		result = StringUtils.replace(result, "April"    , "Apr");
		result = StringUtils.replace(result, "June"     , "Jun");
		result = StringUtils.replace(result, "July"     , "Jul");
		result = StringUtils.replace(result, "August"   , "Aug");
		result = StringUtils.replace(result, "September", "Sep");
		result = StringUtils.replace(result, "October"  , "Oct");
		result = StringUtils.replace(result, "November" , "Nov");
		result = StringUtils.replace(result, "December" , "Dec");

		return result;
	}



	public static String transformDateToStringMail(String dateStr) {
		LocalDate localdate = LocalDate.parse(dateStr, DateUtils.LOCAL_DATE_FORMAT);

//        String maildate = StringUtils.trim(StringUtils.split(localdate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).withLocale(Locale.UK)), ",")[1]);
//        String[] parts = StringUtils.split(maildate, " ", 2);
//        String mailDateFinal = parts[0] + DateUtils.suffixes[Integer.valueOf(parts[0])] + " " + parts[1];

		String maildate = DateUtils.replaceMonthPartToFull(localdate.format(DateUtils.MAIL_DATE_FORMAT));
		String[] parts = StringUtils.split(maildate, " ", 2);
		Integer day = Integer.parseInt(parts[0]);
		String mailDateFinal = day.toString() + DateUtils.suffixes[Integer.valueOf(parts[0])] + " " + parts[1];

		return mailDateFinal;
	}

	public static  LocalDate transformStringMailToDate(String dateStr) {
		String[] parts = StringUtils.split(dateStr, " ", 2);
		String date = StringUtils.leftPad(parts[0].substring(0, parts[0].length() - 2), 2, "0") + " " + parts[1];
		String date2 = DateUtils.replaceMonthFullToPart(date);

		LocalDate localDate = LocalDate.parse(date2, DateUtils.MAIL_DATE_FORMAT);

		return localDate;
	}
}
