package com.he.rating.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value ="user")
public class UserController {

    @GetMapping("/test")
    public String test(){
        return "test 123";
    }
}
