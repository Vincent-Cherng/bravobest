package com.bravo.bravobest.common.page;

import java.io.Serializable;
import java.util.List;

/**
 * 用于分页的对象
 */
public class Pager implements Serializable {

    private static final long serialVersionUID = 3476127578853770932L;

    private int page = 1;// 页码，默认是第一页
    private int rows = 10;// 每页显示的记录数，默认是10
    private int totalRecord;// 总记录数
    private int pageCount;//总页数
    private List data;// 对应的当前页记录

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(int totalRecord) {
        this.totalRecord = totalRecord;
        this.calPageCount();
    }

    public List getData() {
        return data;
    }

    public void setData(List data) {
        this.data = data;
    }

    public void calPageCount() {
        this.pageCount = (int) Math.ceil((this.totalRecord * 1.0) / this.rows);
    }
}


