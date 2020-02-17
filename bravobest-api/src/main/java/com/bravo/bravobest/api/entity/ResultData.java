package com.bravo.bravobest.api.entity;

import java.io.Serializable;

/**
 * 返回消息实体类
 */
public class ResultData implements Serializable {

    private static final long serialVersionUID = 8720470665989721869L;

    private int resultCode;//返回编码

    private String resultMessage;//返回消息


    public ResultData(int resultCode,String resultMessage) {
        this.resultCode = resultCode;
        this.resultMessage = resultMessage;
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }
}
