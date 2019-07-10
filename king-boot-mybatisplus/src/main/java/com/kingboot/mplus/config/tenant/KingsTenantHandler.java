package com.kingboot.mplus.config.tenant;

import com.baomidou.mybatisplus.extension.plugins.tenant.TenantHandler;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.StringValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;


/**
 * <p class="detail">
 * 功能:租户维护处理器
 * </p>
 * @author Kings
 * @ClassName Kings tenant handler.
 * @Version V1.0.
 * @date 2019.07.10 09:31:26
 */
@Slf4j
@Configuration
public class KingsTenantHandler implements TenantHandler {
	/** Properties. */
	@Autowired
	private TenantConfigProperties properties;
	
	/**
	 * 获取租户值
	 *
	 * @return 租户值
	 */
	@Override
	public Expression getTenantId() {
		String tenantId = TenantContextHolder.getTenantId();
		log.debug("当前租户为 >> {}", tenantId);
		return new StringValue(tenantId);
	}

	/**
	 * 获取租户字段名
	 *
	 * @return 租户字段名
	 */
	@Override
	public String getTenantIdColumn() {
		return properties.getColumn();
	}

	/**
	 * 根据表名判断是否进行过滤
	 *
	 * @param tableName 表名
	 * @return 是否进行过滤
	 */
	@Override
	public boolean doTableFilter(String tableName) {
		return !properties.getTables().contains(tableName);
	}
}
