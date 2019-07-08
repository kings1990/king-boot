package com.kingboot.mplus.config;


import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;

/**
 * <p class="detail">
 * 功能:自定义mapper方法
 * </p>
 * @author Kings
 * @ClassName Delete all method.
 * @Version V1.0.
 * @date 2019.07.08 15:27:33
 */
public class DeleteAllMethod extends AbstractMethod {
	
	@Override
	public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
		/* mapper 接口方法名一致 */
		String id = "deleteAll";
		
		/* 执行 SQL ，动态 SQL 参考类 SqlMethod */
		String sql = "delete from " + tableInfo.getTableName();
		
		SqlSource sqlSource = languageDriver.createSqlSource(configuration, sql, modelClass);
		return this.addDeleteMappedStatement(mapperClass, id, sqlSource);
		
	}
}
