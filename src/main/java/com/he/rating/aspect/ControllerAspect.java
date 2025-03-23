package com.he.rating.aspect;

import com.he.rating.common.CommonError;
import com.he.rating.common.CommonRes;
import com.he.rating.common.EmBussinessError;
import com.he.rating.controller.admin.AdminController;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;

@Aspect
@Configuration
public class ControllerAspect {

    @Autowired
    private HttpServletRequest httpServletRequest;

    @Autowired
    private HttpServletResponse httpServletResponse;

    /*
    * scan admin package, and controller
    * route with "AdminPermission" annotation
     */
    @Around("execution(* com.he.rating.controller.admin.*.*(..)) && @annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public Object adminControllerBeforeValidation(ProceedingJoinPoint joinPoint) throws Throwable {

        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        AdminPermission adminPermission = method.getAnnotation(AdminPermission.class);
        if (adminPermission == null) {
            // public route
            Object resultObject = joinPoint.proceed();
            return resultObject;
        }

        // check whether admin is logged in
        String email = (String) httpServletRequest.getSession().getAttribute(AdminController.CURRENT_ADMIN_SESSION);
        if (email == null) {
            if (adminPermission.produceType().equals("text/html")) {
                httpServletResponse.sendRedirect("/admin/admin/login_page");
                return null;
            } else {
                CommonError commonError = new CommonError(EmBussinessError.ADMIN_SHOULD_LOGIN);
                return CommonRes.create(commonError, "fail");
            }
        }

        // continue to next step
        Object resultObject = joinPoint.proceed();
        return resultObject;
    }
}
