package com.he.rating.common;

public class BusinessException extends Exception {

    private CommonError commonError;

    public BusinessException(EmBussinessError emBussinessError) {
        super();
        this.commonError = new CommonError(emBussinessError);
    }

    public BusinessException(EmBussinessError emBussinessError, String errMsg) {
        super();
        this.commonError = new CommonError(emBussinessError);
        this.commonError.setErrMsg(errMsg);
    }

    public CommonError getCommonError() {
        return commonError;
    }

    public void setCommonError(CommonError commonError) {
        this.commonError = commonError;
    }

}
