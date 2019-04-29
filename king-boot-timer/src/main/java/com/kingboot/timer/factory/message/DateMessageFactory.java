package com.kingboot.timer.factory.message;


import com.kingboot.timer.factory.message.interfaces.JobMessageFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateMessageFactory implements JobMessageFactory<Date> {
	public static final String DATE_FORMAT = "yyyy-MM-dd";
	public static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	
	private String format;
	private SimpleDateFormat sdf;
	
	public DateMessageFactory() {
	}
	
	public DateMessageFactory(String format) {
		setFormat(format);
	}
	
	@Override
	public String generateMessage(Date payload) {
		return sdf.format(payload);
	}
	
	public void setFormat(String format) {
		this.format = format;
		setSdf(new SimpleDateFormat(format));
	}
	
	public void setSdf(SimpleDateFormat sdf) {
		this.sdf = sdf;
	}
}
