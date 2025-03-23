package com.he.rating.common;

public enum EmBussinessError {

    // general error
    NO_OBJECT_FOUND(10001, "No object found"),
    UNKNOWN_ERROR(10002, "Unknown error"),
    NO_HANDLER_FOUND(10003, "No route found"),
    BIND_EXCEPTION_ERROR(10004, "Request params error"),
    PARAMETER_VALIDATION_ERROR(10005, "Request parameter validation error"),

    // user error
    REGISTER_DUP_FAIL(20001, "Duplicated user with telephone"),
    LOGIN_FAIL(20002, "Telephone or password incorrect");

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
