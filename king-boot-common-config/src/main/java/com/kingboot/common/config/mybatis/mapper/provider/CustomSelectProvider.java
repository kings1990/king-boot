package com.kingboot.common.config.mybatis.mapper.provider;

import org.apache.ibatis.mapping.MappedStatement;
import tk.mybatis.mapper.entity.EntityColumn;
import tk.mybatis.mapper.mapperhelper.EntityHelper;
import tk.mybatis.mapper.mapperhelper.MapperHelper;
import tk.mybatis.mapper.mapperhelper.MapperTemplate;
import tk.mybatis.mapper.mapperhelper.SqlHelper;

import java.util.Iterator;
import java.util.Set;


/**
 * <p class="detail">
 * 功能:自定义查询provider
 * </p>
 * @author Kings
 * @ClassName Custom select provider.
 * @Version V1.0.
 * @date 2016.03.21 15:06:20
 */
public class CustomSelectProvider extends MapperTemplate {
	/**
	 * Instantiates a new Custom select provider.
	 * @param mapperClass  the mapper class
	 * @param mapperHelper the mapper helper
	 */
	public CustomSelectProvider(Class<?> mapperClass, MapperHelper mapperHelper) {
		super(mapperClass, mapperHelper);
	}
	
	/**
	 * <p class="detail">
	 * 功能:根据主键集合或数组查询
	 * </p>
	 * @param ms :
	 *
	 * @return string
	 * @author Kings
	 * @date 2016.03.21 15:06:20
	 */
	public String selectByPrimaryKeys(MappedStatement ms) {
		Class<?> entityClass = getEntityClass(ms);
		//修改返回值类型为实体类型
		setResultType(ms, entityClass);
		StringBuilder sql = new StringBuilder();
		sql.append(SqlHelper.selectAllColumns(entityClass));
		sql.append(SqlHelper.fromTable(entityClass, tableName(entityClass)));
		sql.append("<where>");
		sql.append("<foreach collection=\"ids\" item=\"id\" separator=\" or \" open=\"(\" close=\")\">");
		Set<EntityColumn> columnList = EntityHelper.getPKColumns(entityClass);
		Iterator columnListIterator = columnList.iterator();
		int idx = 0;
		int pkSize = columnList.size();//pk size
		if (pkSize == 1) {
			EntityColumn column = (EntityColumn) columnListIterator.next();
			sql.append(column.getColumnEqualsHolder());
		} else {
			while (columnListIterator.hasNext()) {
				EntityColumn column = (EntityColumn) columnListIterator.next();
				if (idx == 0) {
					sql.append("(" + column.getColumnEqualsHolder("id"));
				} else {
					sql.append(" and " + column.getColumnEqualsHolder("id"));
				}
				if (idx == pkSize - 1) {
					sql.append(")");
				}
				idx++;
			}
		}
		sql.append("</foreach>");
		sql.append("</where>");
		return sql.toString();
	}
}
