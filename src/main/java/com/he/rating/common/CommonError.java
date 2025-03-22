package com.he.rating.common;

public class CommonError {

    private Integer errCode;

    private String errMsg;

    public CommonError(Integer errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    public CommonError(EmBussinessError emBussinessError) {
        this.errCode = emBussinessError.getErrCode();
        this.errMsg = emBussinessError.getErrMsg();
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public Integer getErrCode() {
        return errCode;
    }

    public void setErrCode(Integer errCode) {
        this.errCode = errCode;
    }
}
