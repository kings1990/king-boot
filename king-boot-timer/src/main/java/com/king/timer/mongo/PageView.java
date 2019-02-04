package com.king.timer.mongo;

import java.util.List;

public class PageView {
    private List<?> records;
    private long pageCount;
    private int pageSize = 10;
    private int pageNow = 1;
    private long rowCount;
    private int startPage;
    private int pagecode = 10;
    
    public PageView() {
    }
    
    public PageView(int pageSize, int pageNow) {
        this.pageSize = pageSize;
        this.pageNow = pageNow;
    }
    
    public PageView(int pageNow) {
        this.pageNow = pageNow;
        this.startPage = (this.pageNow - 1) * this.pageSize;
    }
    
    public int getFirstResult() {
        return (this.pageNow - 1) * this.pageSize;
    }
    
    public int getPagecode() {
        return this.pagecode;
    }
    
    public void setPagecode(int pagecode) {
        this.pagecode = pagecode;
    }
    
    public void setQueryResult(long rowCount, List<?> records) {
        this.setRowCount(rowCount);
        this.setRecords(records);
    }
    
    public List<?> getRecords() {
        return this.records;
    }
    
    public void setRecords(List<?> records) {
        this.records = records;
    }
    
    public int getPageNow() {
        return this.pageNow;
    }
    
    public void setPageNow(int pageNow) {
        this.pageNow = pageNow;
    }
    
    public long getPageCount() {
        return this.pageCount;
    }
    
    public void setPageCount(long pageCount) {
        this.pageCount = pageCount;
    }
    
    public int getPageSize() {
        return this.pageSize;
    }
    
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
    
    public long getRowCount() {
        return this.rowCount;
    }
    
    public void setRowCount(long rowCount) {
        this.rowCount = rowCount;
        this.setPageCount(this.rowCount % (long) this.pageSize == 0L ? this.rowCount / (long) this.pageSize : this.rowCount / (long) this.pageSize + 1L);
    }
    
    public int getStartPage() {
        return this.startPage;
    }
    
    public void setStartPage(int startPage) {
        this.startPage = startPage;
    }
    
    public String toString() {
        return "PageView [ pageCount=" + this.pageCount + ", pageSize=" + this.pageSize + ", pageNow=" + this.pageNow + ", rowCount=" + this.rowCount + ", startPage=" + this.startPage + ", pagecode=" + this.pagecode + "]";
    }
    
}