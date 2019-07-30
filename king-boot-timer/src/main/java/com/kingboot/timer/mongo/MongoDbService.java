package com.kingboot.timer.mongo;

import org.springframework.data.mongodb.core.query.Query;


/**
 * <p class="detail">
 * 功能:mongobd实现
 * </p>
 * @author Kings
 * @ClassName Mongo db service.
 * @Version V1.0.
 * @date 2019.07.30 10:36:52
 */
public interface MongoDbService {
	
	
	/**
	 * <p class="detail">
	 * 功能: 根据Query分页查询
	 * </p>
	 * @param <T>        the type parameter
	 * @param query      :
	 * @param page       :
	 * @param clazz      :
	 * @param collection :
	 *
	 * @return list page
	 * @author hbprotoss
	 * @date 2017.05.12 13:57:48
	 */
	<T> MongoPage<T> findPage(Query query, MongoPage<T> page, Class<T> clazz, String collection);
	
	
}
