package com.kingboot.mplus.config;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @description： mybatis-plus专用分页工具
 */
@Data
public class ResultPage<T> implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**
	 * 当前页数
	 */
	private Long pageNum = 1L;
	/**
	 * 每页记录数
	 */
	private Long pageSize = 10L;
	/**
	 * 列表数据
	 */
	private List<T> records;
	/**
	 * 总页数
	 */
	private Long count = 1L;
	/**
	 * 总记录数
	 */
	private Long total = 1L;
	/**
	 * 第一页
	 */
	private boolean isFirstPage = false;
	/**
	 * 最后一页
	 */
	private boolean isLastPage = false;
	
	public ResultPage() {
		this.records = new ArrayList<>();
	}
	
	/**
	 * 分页
	 * @param entities 列表数据
	 * @param total    总记录数
	 * @param pageSize 每页记录数
	 * @param pageNum  当前页数
	 */
	public ResultPage(List<T> entities, Long total, Long pageSize, Long pageNum) {
		this.records = entities;
		this.total = total;
		this.pageSize = pageSize;
		this.pageNum = pageNum;
		this.count = (long) Math.ceil((double) total / pageSize);
		
		if (entities != null) {
			this.judgePageBoudary();
		}
	}
	
	/**
	 * 分页
	 */
	public ResultPage(Page<T> page) {
		this.records = page.getRecords();
		this.total = page.getTotal();
		this.pageSize = page.getSize();
		this.pageNum = page.getCurrent();
		this.count = page.getPages();
		
		this.judgePageBoudary();
	}
	
	/**
	 * 分页
	 */
	public ResultPage(IPage<T> page) {
		this.records = page.getRecords();
		this.total = page.getTotal();
		this.pageSize = page.getSize();
		this.pageNum = page.getCurrent();
		this.count = page.getPages();
		
		this.judgePageBoudary();
	}
	
	/**
	 * 参数
	 */
	private void judgePageBoudary() {
		this.isFirstPage = this.pageNum == 1L;
		this.isLastPage = this.pageNum.equals(this.count);
	}
}