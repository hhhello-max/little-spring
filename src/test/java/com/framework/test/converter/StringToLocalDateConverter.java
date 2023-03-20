package com.framework.test.converter;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.framework.core.convert.converter.Converter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class StringToLocalDateConverter implements Converter<String, DateTime> {

	private final DateTimeFormatter DATE_TIME_FORMATTER;

	public StringToLocalDateConverter(String pattern) {
		DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(pattern);
	}

	@Override
	public DateTime convert(String source) {
		return DateUtil.parse(source, "yyyy-MM-dd");
	}

}
