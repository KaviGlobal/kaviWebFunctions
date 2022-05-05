package com.kavi.web.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CommonUtility {

	 public static String convertLocalDateTimeToYYYYMMDDHHmmss(LocalDateTime dateTime) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
			return dateTime.format(formatter);
		}
}
