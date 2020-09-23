package com.ng.domain;

import java.util.List;

public class Page<T> {

    public static final int PAGE_SIZE = 4;

    private Integer pageNo;
    private Integer pageTotal;
    private Integer totalCount;
    private Integer pageSize = PAGE_SIZE;
    private List<T> items;
    private String url;

    public Page() {
    }

    public Page(Integer pageNo, Integer pageTotal, Integer totalCount, Integer pageSize, List<T> items, String url) {
        this.pageNo = pageNo;
        this.pageTotal = pageTotal;
        this.totalCount = totalCount;
        this.pageSize = pageSize;
        this.items = items;
        this.url = url;
    }

    public static int getPageSize() {
        return PAGE_SIZE;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getPageNo() {
        return pageNo;
    }


    public Integer getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(Integer pageTotal) {
        this.pageTotal = pageTotal;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public void setPageNo(Integer pageNo) {
        if (pageNo < 1)
            pageNo = 1;
        if (pageNo > pageTotal)
            pageNo = pageTotal;
        this.pageNo = pageNo;
    }
}
