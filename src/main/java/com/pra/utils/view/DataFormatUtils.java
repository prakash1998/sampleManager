package com.pra.utils.view;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.pra.utils.commons.PrimaryKeyConverter;

public class DataFormatUtils {

	public static String formatDate(LocalDate date) {
		// DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-uuuu");
		return date.format(formatter);

	}

	public static String formatNum(Double val) {
		return String.format("%.2f", val);
	}

	// public static String formatNum(Float val) {
	// return String.format("%.2f", val);
	// }

}
