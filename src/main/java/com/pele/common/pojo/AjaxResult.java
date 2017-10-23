package com.pele.common.pojo;

/*
*@author: pele
*@time: 2017/10/21 16:05
*@project: work-attendance
*@description:ajax请求返回的统一的格式
*/
public class AjaxResult {
    private int status;
    private Object data;

    AjaxResult(int status){
        this.status=status;
    }

    AjaxResult(int status,Object data){
        this.status = status;
        this.data = data;
    }

    public AjaxResult(){}

    public static AjaxResult build(int status,Object data){
        return new AjaxResult(status,data);
    }

    public static AjaxResult ok(Object data){
        return new AjaxResult(200,data);
    }

    public static AjaxResult ok(){
        return new AjaxResult(200);
    }

    public void set(int status,Object data){
        this.status = status;
        this.data = data;
    }

    public void set(int status){
        this.status = status;
    }

    public void set(Object data){
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Object getData() {
        return this.data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
