package com.he.rating.common;

public enum EmBussinessError {

    NO_OBJECT_FOUND(10001, "No object found");

    private Integer errCode;

    private String errMsg;

    EmBussinessError(Integer errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    public Integer getErrCode() {
        return errCode;
    }

    public void setErrCode(Integer errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
}
