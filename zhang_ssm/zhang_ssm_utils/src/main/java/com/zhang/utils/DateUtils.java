package com.zhang.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

	//date转换字符串
	public static String date2str(Date date,String pttr){
		SimpleDateFormat sdf = new SimpleDateFormat(pttr);
		String format = sdf.format(date);
		return format;
	}

	//字符串转换日期
	public static Date str2Date(String str,String pttr)  {
		SimpleDateFormat sdf = new SimpleDateFormat(pttr);
		Date date = null;
		try {
			date = sdf.parse(str);
		} catch (ParseException e) {

		}
		return date;
	}
}
