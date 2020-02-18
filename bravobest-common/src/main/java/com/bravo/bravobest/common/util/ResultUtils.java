package com.bravo.bravobest.common.util;

import com.bravo.bravobest.api.entity.ResultData;

public class ResultUtils {


    public static ResultData success() {
        ResultData resultData = new ResultData(ResultData.DEFAULT_SUCCESS_CODE, "成功",null);
        return resultData;
    }

    public static ResultData success(int code,Object data) {
        ResultData resultData = new ResultData(code, "",data);
        return resultData;
    }

    public static ResultData success(Object data) {
        ResultData resultData = new ResultData(ResultData.DEFAULT_SUCCESS_CODE, "",data);
        return resultData;
    }

    public static ResultData success(int code,String message,Object data) {
        ResultData resultData = new ResultData(code, message,data);
        return resultData;
    }

    public static ResultData success(String message) {
        ResultData resultData = new ResultData(ResultData.DEFAULT_SUCCESS_CODE, message,null);
        return resultData;
    }

    public static ResultData fail() {
        ResultData resultData = new ResultData(ResultData.DEFAULT_FAIL_CODE, "失败");
        return resultData;
    }

    public static ResultData fail(int code,String message) {
        ResultData resultData = new ResultData(code, message);
        return resultData;
    }

    public static ResultData fail(String message) {
        ResultData resultData = new ResultData(ResultData.DEFAULT_FAIL_CODE, message);
        return resultData;
    }
}
