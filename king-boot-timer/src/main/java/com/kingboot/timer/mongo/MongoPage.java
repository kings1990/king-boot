package com.kingboot.timer.mongo;

import java.util.List;

/**
 * <p class="detail">
 * 功能:mongo分页
 * </p>
 * @param <T> the type parameter
 *
 * @author Kings
 * @ClassName Mongo page.
 * @Version V1.0.
 * @date 2019.07.30 10:37:18
 */
public class MongoPage<T> {
	private List<T> list;
	private int pageNum = 1;
	private int pageSize = 10;
	private long total;
	
	
	private int totalPages;
	
	public List<T> getList() {
		return list;
	}
	
	public void setList(List<T> list) {
		this.list = list;
	}
	
	public long getTotal() {
		return total;
	}
	
	public void setTotal(long total) {
		this.total = total;
	}
	
	public int getPageNum() {
		return pageNum;
	}
	
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	
	public int getPageSize() {
		return pageSize;
	}
	
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	public int getTotalPages() {
		return totalPages;
	}
	
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	
	public void setTotalPages() {
		//设置总页数
		this.totalPages = (int) (total % pageSize == 0 ? total / pageSize : total / pageSize + 1);
	}
	
}
