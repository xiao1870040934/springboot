package cn.ccsu.store.util;

import java.io.Serializable;

/**
 * @Author 潇洒哥queen
 * @Date 2022/3/27 12:50
 * @Version 1.0
 */
public class JsonResult<E> implements Serializable {
    //状态码
    private Integer state;
    //描述信息
    private String message;
    //数据
    private E data;

    public JsonResult() {
    }

    public JsonResult(Integer state) {
        this.state = state;
    }

    public JsonResult(Throwable e) {
        this.message = e.getMessage();
    }

    public JsonResult(Integer state, E data) {
        this.state = state;
        this.data = data;
    }

    public JsonResult(Integer state, String message, E data) {
        this.state = state;
        this.message = message;
        this.data = data;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }
}
