package com.kingboot.mplus.generator;


import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.Test;

/**
 * <p class="detail">
 * 功能:测试生成代码
 * </p>
 * @author Kings
 * @ClassName GeneratorServiceEntity
 * @Version V1.0.
 * @date 2019.07.30 11:16:04
 */
public class GeneratorServiceEntityTest {

	@Test
	public void generateCode() {
		String packageName = "com.kingboot.mplus";
		//user -> UserService, 设置成true: user -> IUserService
		boolean serviceNameStartWithIlletter= false;
		generateByTables(serviceNameStartWithIlletter, packageName, "user");
	}

	private void generateByTables(boolean serviceNameStartWithIletter, String packageName, String... tableNames) {
		// 注入自定义配置，可以在 VM 中使用 cfg.abc 【可无】
		InjectionConfig cfg = new InjectionConfig() {
			@Override
			public void initMap() {

			}
		};

		GlobalConfig config = new GlobalConfig();
		String dbUrl = "jdbc:mysql://localhost:3306/boot";
		DataSourceConfig dataSourceConfig = new DataSourceConfig();
		dataSourceConfig.setDbType(DbType.MYSQL)
			.setUrl(dbUrl)
			.setUsername("root")
			.setPassword("1234")
			.setDriverName("com.mysql.cj.jdbc.Driver");
		StrategyConfig strategyConfig = new StrategyConfig();
		strategyConfig
			.setCapitalMode(true)
			.setEntityLombokModel(true)
			.setNaming(NamingStrategy.underline_to_camel)
			//修改替换成你需要的表名，多个表名传数组
			.setInclude(tableNames);
		config.setActiveRecord(false)
			.setAuthor("Kings")
			.setOutputDir("/Users/wilson/Desktop/mp/")
			.setFileOverride(true);
		if (!serviceNameStartWithIletter) {
			config.setServiceName("%sService");
		}
		new AutoGenerator().setGlobalConfig(config)
			.setCfg(cfg)
			.setDataSource(dataSourceConfig)
			.setStrategy(strategyConfig)
			.setPackageInfo(
				new PackageConfig()
					.setParent(packageName)
					.setController("controller")
					.setEntity("entity")
			).execute();
	}

	private void generateByTables(String packageName, String... tableNames) {
		generateByTables(true, packageName, tableNames);
	}
}