package com.kingboot.mplus.config;

import com.baomidou.mybatisplus.core.parser.ISqlParser;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import com.baomidou.mybatisplus.extension.plugins.SqlExplainInterceptor;
import com.baomidou.mybatisplus.extension.plugins.tenant.TenantHandler;
import com.baomidou.mybatisplus.extension.plugins.tenant.TenantSqlParser;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


@Configuration
public class MybatisPlusConfig {
	
	
	// /**
	//  * 创建租户维护处理器对象
	//  *
	//  * @return 处理后的租户维护处理器
	//  */
	// @Bean
	// @ConditionalOnMissingBean
	// public KingsTenantHandler jcwlTenantHandler() {
	// 	return new KingsTenantHandler();
	// }
	/**
	 * 分页插件
	 *
	 * @return PaginationInterceptor
	 */
	@Bean
	@ConditionalOnMissingBean
	public PaginationInterceptor paginationInterceptor(TenantHandler tenantHandler) {
		PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
		List<ISqlParser> sqlParserList = new ArrayList<>();
		TenantSqlParser tenantSqlParser = new TenantSqlParser();
		tenantSqlParser.setTenantHandler(tenantHandler);
		sqlParserList.add(tenantSqlParser);
		paginationInterceptor.setSqlParserList(sqlParserList);
		return paginationInterceptor;
	}
	
	//注入自定义SQL
	@Bean
	@ConditionalOnMissingBean
	public MySqlInjector mySqlInjector() {
		return new MySqlInjector();
	}
	
	
	@Bean
	@ConditionalOnMissingBean
	public SQLFilter sqlFilter() {
		return new SQLFilter();
	}
	
	//执行分析插件
	@Bean
	@Profile ({"dev","test"})// 设置 dev test 环境开启
	public SqlExplainInterceptor sqlExplainInterceptor(){
		SqlExplainInterceptor sqlExplainInterceptor = new SqlExplainInterceptor();
		Properties properties = new Properties();
		properties.put("stopProceed",true);
		sqlExplainInterceptor.setProperties(properties);
		return sqlExplainInterceptor;
	}
	
	//性能分析插件
	@Bean
	@Profile ({"dev","test"})// 设置 dev test 环境开启
	public PerformanceInterceptor performanceInterceptor() {
		return new PerformanceInterceptor();
	}
	
	//乐观锁
	@Bean
	public OptimisticLockerInterceptor optimisticLockerInterceptor() {
		return new OptimisticLockerInterceptor();
	}
	
	//公共字段
	@Bean
	public MyMetaObjectHandler myMetaObjectHandler(){
		return new MyMetaObjectHandler();
	}
}
