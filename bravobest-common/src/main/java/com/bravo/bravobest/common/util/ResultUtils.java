package com.bravo.bravobest.common.util;

import com.bravo.bravobest.api.entity.ResultData;

public class ResultUtils {



    public static ResultData success() {
        ResultData resultData = new ResultData(0, "成功");
        return resultData;
    }

    public static ResultData success(int code,String message) {
        ResultData resultData = new ResultData(code, message);
        return resultData;
    }

    public static ResultData success(String message) {
        ResultData resultData = new ResultData(0, message);
        return resultData;
    }

    public static ResultData fail() {
        ResultData resultData = new ResultData(-1, "失败");
        return resultData;
    }

    public static ResultData fail(int code,String message) {
        ResultData resultData = new ResultData(code, message);
        return resultData;
    }

    public static ResultData fail(String message) {
        ResultData resultData = new ResultData(-1, message);
        return resultData;
    }
}
