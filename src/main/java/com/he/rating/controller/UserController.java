package com.he.rating.controller;

import com.he.rating.common.CommonRes;
import com.he.rating.model.UserModel;
import com.he.rating.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value ="user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/test")
    public String test(){
        return "test 123";
    }

    @GetMapping("/get")
    public CommonRes getUserModel(
            @RequestParam(name="id") Integer id
    ) {
        UserModel user = userService.getUser(id);
        return CommonRes.create(user);
    }
}
