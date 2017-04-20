package com.example.controller;

import com.example.Utils.MD5;
import com.example.domain.UserEntity;
import com.example.service.FlightService;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by iam24 on 17/4/11.
 */
@Controller
public class IndexController {

    @Autowired
    UserService userService;

    @Autowired
    FlightService flightService;

    @RequestMapping("/")
    public String index(Model model){
        model.addAttribute("mes", "helloword");
        return "index";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String loginOut(HttpSession session) {
        session.removeAttribute("user");
        return "redirect:/login";
    }

    /**
     * 注册
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerGet(){
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@ModelAttribute(value = "user") UserEntity new_user,
                           HttpServletResponse response, Model model) throws IOException {
        String result = userService.register(new_user);
        if (result.equals("注册成功")){
            response.sendRedirect("/login");
        }
        model.addAttribute("result", result);
        return "register";
    }

    /**
     * 登录
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginn(){
        return "login";
    }
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam("name") String name,
                        @RequestParam("password") String password,
                        HttpSession session){
        String result = userService.login(name, MD5.getMD5(password), session);
        if (result.equals("登陆成功1")) {
            return "redirect:/admin/alluser";
        }else
            if (result.equals("登录成功3")){
                return "redirect:/user/allflight";
            }
        return "redirect:/login";
    }

    @RequestMapping(value = "/401", method = RequestMethod.GET)
    @ResponseBody
    public String admin401(){
        return "权限不足!";
    }
}
