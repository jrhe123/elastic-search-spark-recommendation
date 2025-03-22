package com.he.rating.advice;

import com.he.rating.common.BusinessException;
import com.he.rating.common.CommonError;
import com.he.rating.common.CommonRes;
import com.he.rating.common.EmBussinessError;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public CommonRes handleException(
            HttpServletRequest servletRequest,
            HttpServletResponse servletResponse,
            Exception ex
    ) {
        if (ex instanceof BusinessException) {

            return CommonRes.create(
                    ((BusinessException) ex).getCommonError(),
                    "fail"
            );
        } else if (ex instanceof NoHandlerFoundException) {
            return CommonRes.create(
                    new CommonError(
                            EmBussinessError.NO_HANDLER_FOUND
                    ),
                    "fail"
            );
        } else {
            CommonError commonError = new CommonError(
                    EmBussinessError.UNKNOWN_ERROR
            );
            return CommonRes.create(
                    commonError,
                    "fail"
            );
        }
    }
}
