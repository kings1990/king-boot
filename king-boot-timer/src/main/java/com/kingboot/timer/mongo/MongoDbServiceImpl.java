package com.kingboot.timer.mongo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;


@Service
public class MongoDbServiceImpl implements MongoDbService {
	/** The constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(MongoDbServiceImpl.class);
	
	/** Mongo template. */
	@Autowired
	private MongoTemplate mongoTemplate;
	
	
	@Override
	public <T> MongoPage<T> findPage(Query query, MongoPage<T> page, Class<T> clazz, String collection) {
		page.setTotal(mongoTemplate.count(query, clazz, collection));
		query = MongoUtil.getPageQuery(query, page);
		page.setList(mongoTemplate.find(query, clazz, collection));
		return page;
	}
	
}
