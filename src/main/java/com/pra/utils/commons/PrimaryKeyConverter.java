package com.pra.utils.commons;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.Month;

public class PrimaryKeyConverter {

	private static int primaryKeyDigits = 7;

	public static NumberFormat getFormatter() {
		NumberFormat primaryKeyFormatter = NumberFormat.getIntegerInstance();
		primaryKeyFormatter.setMaximumIntegerDigits(primaryKeyDigits);
		primaryKeyFormatter.setMinimumIntegerDigits(1);
		return primaryKeyFormatter;
	}

	public static int getOrdinary(Integer key) {
		return key % (int) Math.pow(10, primaryKeyDigits);
	}

	// public static int getYearMonth(Integer key) {
	// return key / 1000000;
	// }
	//
	// public static int getMonth(Integer key) {
	// return getYearMonth(key) % 100;
	// }

	public static int getYear(Integer key) {
		return key / (int) Math.pow(10, primaryKeyDigits);
	}

	public static String formatKey(Integer key, LocalDate date, String prefix) {
		return prefix.concat("-").concat(String.valueOf(getOrdinary(key))).concat("-")
				.concat(String.format("%2d", date.getMonth().getValue())).concat(String.valueOf(date.getYear()%100));
	}
	
	public static String formatKey(Integer key, String prefix) {
		return prefix.concat("-").concat(String.valueOf(getOrdinary(key)));
	}
	
	public static String formatKey(Integer key) {
		return String.valueOf(getOrdinary(key));
	}

	public static Integer convertFromOrdinary(Integer id, LocalDate date) {
		int year = getFinancialYear(date);
		return year * (int) Math.pow(10, primaryKeyDigits) + id;
	}
	
	public static int getFinancialYear(LocalDate date) {
		if( date.getMonth().getValue() < Month.APRIL.getValue())
			return (date.getYear() - 1) % 1000;
		return date.getYear() % 1000;
	}

//	public static String getNextOrdinary(Integer id,LocalDate date) {
//		if (LocalDate.now().getMonth() == Month.MARCH) {
//			if (date.getMonth() != Month.MARCH) {
//				return "1";
//			}
//		}
//		return String.valueOf(getOrdinary(id) + 1);
//	}

}
