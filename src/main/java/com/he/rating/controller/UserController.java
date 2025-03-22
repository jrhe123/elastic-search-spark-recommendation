package com.he.rating.controller;

import com.he.rating.common.BusinessException;
import com.he.rating.common.CommonError;
import com.he.rating.common.CommonRes;
import com.he.rating.common.EmBussinessError;
import com.he.rating.model.UserModel;
import com.he.rating.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping(value ="user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/index")
    public ModelAndView index() {
        String name = "roytest";
        ModelAndView mav = new ModelAndView("/index.html");
        mav.addObject("name", name);
        return mav;
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
