package com.pra.utils.commons;

import java.util.regex.Pattern;

public class RegExUtils {

	public static String regExLiteral(String s) {
//		return Pattern.compile(s, Pattern.LITERAL).pattern();
		return s.replaceAll("[-\\[\\]{}()*+?.,\\\\\\\\^$|#\\\\s]", "\\\\$0");
	}
}
