package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by iam24 on 17/3/30.
 */
@Controller
public class UserController {
    @RequestMapping("/")
    @ResponseBody
    public String Index() {
        return "hello world";

    }

}
