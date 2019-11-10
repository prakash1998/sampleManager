package com.pra.utils.commons;

import java.time.LocalDate;
import java.time.Month;

public class PrimaryKeyConverter {

	public static int getOrdinary(Integer key) {
		return key % 1000000;
	}

	public static int getYearMonth(Integer key) {
		return key / 1000000;
	}

	public static int getMonth(Integer key) {
		return getYearMonth(key) % 100;
	}

	public static int getYear(Integer key) {
		return getYearMonth(key) / 100;
	}

	public static String getString(Integer key) {
		return "CI".concat("-").concat(String.valueOf(getOrdinary(key))).concat("-")
				.concat(String.format("%2d",getMonth(key))).concat(String.valueOf(getYear(key)));
	}

	public static String getString(Integer key , String prefix) {
		return prefix.concat("-").concat(String.valueOf(getOrdinary(key))).concat("-")
				.concat(String.format("%2d",getMonth(key))).concat(String.valueOf(getYear(key)));
	}
	public static Integer convertFromOrdinary(Integer id, LocalDate date) {
		int newId = date.getYear() % 100;
		newId = newId * 100 + date.getMonth().getValue();
		return newId * 1000000 + id;
	}

	public static Integer getNextId(Integer id, LocalDate date) {
		if (date.getMonth() == Month.MARCH) {
			if (getMonth(id) != 3) {
				return 1;
			}
		}
		return getOrdinary(id) + 1;
	}

}
