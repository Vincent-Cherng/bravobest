package com.bravo.bravobest.api.entity;

import java.io.Serializable;

/**
 * 返回消息实体类
 */
public class ResultData implements Serializable {


    public static final Integer DEFAULT_SUCCESS_CODE = 0;
    public static final Integer DEFAULT_FAIL_CODE = -1;

    private static final long serialVersionUID = 8720470665989721869L;

    private int code;//返回信息编码

    private String message;//返回信息提示

    private Object data;//返回的实际数据

    public ResultData(int code,String message,Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ResultData(int code,String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
