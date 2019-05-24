package com.cloundwisdom.im.common.base;

/**
 * 统一数据格式
 * @param <T>
 */
public class BaseResponse<T> {

    private int code;

    private String msg;

    private T data;

    private T list;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public T getList() {
        return list;
    }

    public void setList(T list) {
        this.list = list;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
