package com.kingboot.mplus.generator.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kingboot.mplus.generator.entity.GenConfig;

import java.util.List;
import java.util.Map;


/**
 * <p class="detail">
 * 功能:生成表service
 * </p>
 * @author Kings
 * @ClassName SysGeneratorService
 * @Version V1.0.
 * @date 2019.07.30 11:27:15
 */
public interface SysGeneratorService {
	
	/**
	 * <p class="detail">
	 * 功能:生成代码
	 * </p>
	 * @param tableNames :表名
	 *
	 * @return byte [ ]
	 * @author Kings
	 * @date 2019.07.30 11:27:04
	 */
	byte[] generatorCode(GenConfig tableNames);
	
	
	/**
	 * <p class="detail">
	 * 功能:分页查询表
	 * </p>
	 * @param page      :分页对象
	 * @param tableName :表名
	 *
	 * @return page page
	 * @author Kings
	 * @date 2019.07.30 11:26:47
	 */
	IPage<List<Map<String, Object>>> getPage(Page page, String tableName);
}
