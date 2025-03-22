package com.he.rating.common;

public class BusinessException extends Exception {

    private CommonError commonError;

    public BusinessException(EmBussinessError emBussinessError) {
        super();
        this.commonError = new CommonError(emBussinessError);
    }
}
