package com.he.rating.common;

public class CommonRes {

    // success or fail
    private String status;

    // if success, return data
    // if fail, return error code
    private Object data;

    public static CommonRes create(Object data) {
        return CommonRes.create(data, "success");
    }

    public static CommonRes create(Object data, String status) {
        CommonRes commonRes = new CommonRes();
        commonRes.setData(data);
        commonRes.setStatus(status);
        return commonRes;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
