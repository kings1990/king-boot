package com.kingboot.mplus.config;

import io.swagger.annotations.ApiParam;

import java.io.Serializable;

/**
 * @ClassName： 中文名：【分页查询参数】
 */
public class QueryPage implements Serializable {
	@ApiParam (value="当前页",required=false, example="")
	private Long pageNum = 0L;

	@ApiParam (value="每页数量",required=false, example="")
	private Long pageSize;

	@ApiParam (value="排序字段",required=false, example="")
	private String[] ascs;

	@ApiParam (value="排序方式",required=false, example="")
	private String[] descs;

	@ApiParam (value="",required=false, example="", hidden=true)
	private boolean optimizeCountSql;

	@ApiParam (value="",required=false, example="", hidden=true)
	private boolean isSearchCount;

	public QueryPage() {
		this.pageNum = 1L;
		this.pageSize = 10L;
		this.optimizeCountSql = true;
		this.isSearchCount = true;
	}

	public Long getPageNum() {
		return pageNum;
	}

	public void setPageNum(Long pageNum) {
		this.pageNum = pageNum;
	}

	public Long getPageSize() {
		return pageSize;
	}

	public void setPageSize(Long pageSize) {
		this.pageSize = pageSize;
	}

	public String[] getAscs() {
		return ascs;
	}

	public void setAscs(String[] ascs) {
		this.ascs = ascs;
	}

	public String[] getDescs() {
		return descs;
	}

	public void setDescs(String[] descs) {
		this.descs = descs;
	}

	public boolean isOptimizeCountSql() {
		return optimizeCountSql;
	}

	public void setOptimizeCountSql(boolean optimizeCountSql) {
		this.optimizeCountSql = optimizeCountSql;
	}

	public boolean isSearchCount() {
		return isSearchCount;
	}

	public void setSearchCount(boolean searchCount) {
		isSearchCount = searchCount;
	}
	public void setCurrent(Long pageNum) {
		this.pageNum = pageNum;
	}
	public void setSize(Long pageSize) {
		this.pageSize = pageSize;
	}




}
