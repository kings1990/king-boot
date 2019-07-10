package com.kingboot.mplus.config;


import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;

import java.time.LocalDateTime;

public class MyMetaObjectHandler implements MetaObjectHandler {
	
	@Override
	public void insertFill(MetaObject metaObject) {
	
	}
	
	@Override
	public void updateFill(MetaObject metaObject) {
		LocalDateTime now = LocalDateTime.now();
		setUpdateFieldValByName("lastUpdatedTime", now, metaObject);
	}
}