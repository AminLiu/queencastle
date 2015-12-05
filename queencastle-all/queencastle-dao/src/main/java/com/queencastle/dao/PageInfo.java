package com.queencastle.dao;

import java.util.List;

/**
 * 分页显示组件，泛型约束
 * 
 * @author YuDongwei
 *
 * @param <T>
 */
public class PageInfo<T> {
    private Integer page;
    private Integer total;
    private List<T> rows;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}
