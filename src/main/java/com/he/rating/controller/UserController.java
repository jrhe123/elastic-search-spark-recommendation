package com.he.rating.controller;

import com.he.rating.common.*;
import com.he.rating.model.UserModel;
import com.he.rating.request.LoginReq;
import com.he.rating.request.RegisterReq;
import com.he.rating.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping(value ="user")
public class UserController {

    public static final String CURRENT_USER_SESSION = "currentUserSession";

    @Autowired
    private UserService userService;

    @Autowired
    private HttpServletRequest httpServletRequest;

    @GetMapping("/index")
    public ModelAndView index() {
        String name = "roytest";
        ModelAndView mav = new ModelAndView("/index.html");
        mav.addObject("name", name);
        return mav;
    }

    @PostMapping("/register")
    public CommonRes register(
            @Valid @RequestBody RegisterReq registerReq,
            BindingResult bindingResult) throws BusinessException, NoSuchAlgorithmException {

        if (bindingResult.hasErrors()) {
            throw new BusinessException(
                    EmBussinessError.PARAMETER_VALIDATION_ERROR,
                    CommonUtil.processErrorString(bindingResult)
            );
        }

        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(registerReq, userModel);

        UserModel registered = userService.register(userModel);
        return CommonRes.create(registered);
    }

    @PostMapping("/login")
    public CommonRes login(
            @Valid @RequestBody LoginReq loginReq,
            BindingResult bindingResult
            ) throws BusinessException, NoSuchAlgorithmException {

        if (bindingResult.hasErrors()) {
            throw new BusinessException(
                    EmBussinessError.PARAMETER_VALIDATION_ERROR,
                    CommonUtil.processErrorString(bindingResult)
            );
        }

        UserModel userModel = userService.login(loginReq.getTelephone(), loginReq.getPassword());

        // add login session
        // NOTE: we can check it with breakpoint
        // & search "httpServletRequest.getSession().getAttribute("currentUserSession")"
        httpServletRequest.getSession().setAttribute(CURRENT_USER_SESSION, userModel);

        return CommonRes.create(userModel);
    }


    @GetMapping("/test")
    public String test(){
        return "test 123";
    }

    @GetMapping("/get")
    public CommonRes getUserModel(
            @RequestParam(name="id") Integer id
    ) throws BusinessException {
        UserModel user = userService.getUser(id);
        if (user == null) {
            throw new BusinessException(EmBussinessError.NO_OBJECT_FOUND);
        }
        return CommonRes.create(user);
    }
}
