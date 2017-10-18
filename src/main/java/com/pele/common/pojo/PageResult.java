package com.pele.common.pojo;

import java.util.ArrayList;
import java.util.List;

/*
*@author: pele
*@time: 2017/10/18 15:11
*@project: work-attendance
*@description:分页结果的包装类
*/
public class PageResult {

    //默认页面大小
    private static final int DEFAULT_PAGE_SIZE=10;
    //当前页号
    private Integer currentPage;
    //页面大小
    private Integer pageSize;
    //总的记录条数
    private Integer totalRows;
    //起始查询行数
    private Integer startRows;
    //总的页数
    private Integer totalPage;
    //查询的数据集
    private List<?> items;

    public Integer getCurrentPage() {
        return currentPage==null ? 1:currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize==null ? DEFAULT_PAGE_SIZE : pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(Integer totalRows) {
        this.totalRows = totalRows;
        //一定使用get方法得到pageSize，否则可能得到是null，报错
        int totalPage = totalRows % this.getPageSize() == 0 ? totalRows/this.getPageSize():totalRows/this.getPageSize()+1;
        setTotalPage(totalPage);
    }

    public Integer getTotalPage() {
        return totalPage==null||totalPage==0 ? 1:totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public List<?> getItems() {
        return items == null ? new ArrayList<>():items;
    }

    public Integer getStartRows() {
        if(startRows==null){
           return  (this.getCurrentPage()-1)*this.getPageSize();
        }
        return startRows;
    }

    public void setStartRows(Integer startRows) {
        this.startRows = startRows;
    }

    public void setItems(List<?> items) {
        this.items = items;
    }
}
