package com.kingboot.timer.factory.message;


import com.kingboot.timer.factory.message.interfaces.JobMessageFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author wilson
 */
public class DateMessageFactory implements JobMessageFactory<Date> {
	public static final String DATE_FORMAT = "yyyy-MM-dd";
	public static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	
	private SimpleDateFormat sdf = null;
	
	public DateMessageFactory() {
	}
	
	public DateMessageFactory(String format) {
		setFormat(format);
	}
	
	@Override
	public String generateMessage(Date payload) {
		synchronized (sdf){
			return sdf.format(payload);
		}
	}
	
	public void setFormat(String format) {
		setSdf(new SimpleDateFormat(format));
	}
	
	public void setSdf(SimpleDateFormat sdf) {
		this.sdf = sdf;
	}
}
