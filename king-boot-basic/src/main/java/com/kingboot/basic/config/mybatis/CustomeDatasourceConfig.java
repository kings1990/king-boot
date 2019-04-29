package com.kingboot.basic.config.mybatis;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;


/**
 * <p class="detail">
 * 功能:多数据源
 * </p>
 * @author Kings
 * @ClassName Mult datasource config.
 * @Version V1.0.
 * @date 2016.01.22 10:47:42
 */
//@Configuration
public class CustomeDatasourceConfig {
	/**
	 * <p class="detail">
	 * 功能:
	 * </p>
	 * @return data source
	 * @author Kings
	 * @date 2016.01.22 10:47:42
	 */
	
	@Value ("${mybatis.type-aliases-package}")
	private String typeAliasesPackage;
	
	@Bean ("dBoot")
	@Primary
	@ConfigurationProperties (prefix = "spring.datasource.dboot")
	public DataSource primaryDataSource() {
		return DataSourceBuilder.create().build();
	}
	
	
	@Bean (name = "sqlSessionFactory-boot")
	@Primary
	public SqlSessionFactory bootSqlSessionFactory(@Qualifier ("dBoot") DataSource dataSource) throws Exception {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(dataSource);
		bean.setTypeAliasesPackage(typeAliasesPackage);
		bean.setConfigLocation(new PathMatchingResourcePatternResolver().getResource("classpath:mybatis-config.xml"));
		bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml"));
		return bean.getObject();
	}
	
}
