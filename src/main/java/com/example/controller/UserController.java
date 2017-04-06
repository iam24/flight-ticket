package com.example.controller;

import com.example.Utils.MD5;
import com.example.domain.UserEntity;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.AttributeOverride;
import javax.servlet.http.HttpSession;

/**
 * Created by iam24 on 17/3/30.
 */
@Controller
public class UserController {

    @Autowired
    UserService userService;
    @RequestMapping("/")
    @ResponseBody
    public String Index() {
        return "hello world";

    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerGet(){
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public String register(@ModelAttribute(value = "user") UserEntity new_user){
        //UserEntity new_user = new UserEntity(name, person_id, 3, password);
        String result = userService.register(new_user);
        return result;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public String login(@RequestParam("name") String name,
                        @RequestParam("password") String password,
                        HttpSession session){
        String result = userService.login(name, MD5.getMD5(password), session);
        return result;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String loginOut(HttpSession session) {
        session.removeAttribute("user");
        return "redirect:/";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public String edit(@RequestParam("old_password") String old_password,
                       @RequestParam("new_password") String new_password, HttpSession session){
        String result = userService.editPassword(old_password, new_password, session);
        return result;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public String delete(@RequestParam("ID") long id, HttpSession session){
        String result = userService.delete(id, session);
        return result;
    }
}
