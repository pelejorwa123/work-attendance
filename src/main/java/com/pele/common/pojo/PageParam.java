package com.pele.common.pojo;


/*
*@author: pele
*@time: 2017/10/18 15:12
*@project: work-attendance
*@description:接收分页查询参数的包装类
*/
public class PageParam extends PageResult{
   //接收前端传来的日期范围字符串，再自己转换成起始日期和结束日期
    private String rangeDate;
    //出勤状态
    private Byte attendStatus;
    //起始日期
    private String startDate;
    //结束日期
    private String endDate;

    private Long userId;

    public String getRangeDate() {
        return rangeDate;
    }

    public void setRangeDate(String rangeDate) {
        this.rangeDate = rangeDate;
    }

    public Byte getAttendStatus() {
        return attendStatus;
    }

    public void setAttendStatus(Byte attendStatus) {
        this.attendStatus = attendStatus;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
