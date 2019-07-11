package com.kingboot.mplus.config;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import com.baomidou.mybatisplus.extension.injector.methods.additional.LogicDeleteByIdWithFill;

import java.util.List;

public class MySqlInjector extends DefaultSqlInjector {
	
	
	@Override
	public List<AbstractMethod> getMethodList(Class<?> mapperClass) {
		List<AbstractMethod> methodList = super.getMethodList(mapperClass);
		methodList.add(new DeleteAllMethod());
		methodList.add(new LogicDeleteByIdWithFill());
		return methodList;
	}
}