package com.kingboot.timer.mongo;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.format.annotation.DateTimeFormat;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * <p class="detail">
 * 功能：mongo查询工具
 * </p>
 * @author <a href="mailto:engineer26@financegt.com">钟利杰</a>
 * @version V1.0
 * @ClassName: MongoUtil
 * @date 2016年5月18日
 */
public class MongoUtil {
	
	/**
	 * <p class="detail">
	 * 功能：mongo分页查询
	 * </p>
	 * @param page           当前页
	 * @param rows           每页显示的行数
	 * @param pageView       分页封装函数
	 * @param collectionName 关系型数据库的表名
	 * @param query          查询条件
	 * @param mongoTemplate
	 *
	 * @return
	 * @author <a href="mailto:engineer26@financegt.com">钟利杰</a>
	 * @date 2016年5月18日
	 */
	public static PageView findPageByMongo(String page, String rows, PageView pageView, String collectionName, Query query, MongoTemplate mongoTemplate) {
		Integer resultSize = mongoTemplate.find(query, Object.class, collectionName).size();//总记录数
		//mongo分页用
		page = page == null ? "1" : page;
		rows = rows == null ? "10" : rows;
		query.skip((Integer.parseInt(page) - 1) * Integer.parseInt(rows));
		query.limit(Integer.parseInt(rows));
		List<Object> result = mongoTemplate.find(query, Object.class, collectionName);
		//总行数
		pageView.setRowCount(resultSize);
		//总页数
		pageView.setPageCount(resultSize % Integer.parseInt(rows) == 0 ? resultSize / Integer.parseInt(rows) : resultSize / Integer.parseInt(rows) + 1);
		pageView.setRecords(result);
		return pageView;
	}
	
	public static Query getPageQuery(Query query, MongoPage page) {
		if (query == null) {
			query = new Query();
		}
		if (page == null) {
			page = new MongoPage();
		}
		query.skip((page.getPageNum() - 1) * page.getPageSize());
		query.limit(page.getPageSize());
		return query;
	}
	
	public static Object getFieldValue(Object object, Field field) throws IllegalAccessException {
		field.setAccessible(true);
		Object value = field.get(object);
		if (value == null) {
			return null;
		}
		
		if (field.isAnnotationPresent(DateTimeFormat.class)) {
			DateTimeFormat format = field.getAnnotation(DateTimeFormat.class);
			SimpleDateFormat sdf = new SimpleDateFormat(format.pattern());
			try {
				return sdf.parse((String) value);
			} catch (ParseException e) {
				throw new IllegalArgumentException(String.format("Can not parse date(%s) with pattern(%s)", value, format.pattern()));
			}
		}
		
		return value;
	}
	
	public static String fuzzyQuery(String key) {
		return String.format(".*%s.*", key);
	}
}

