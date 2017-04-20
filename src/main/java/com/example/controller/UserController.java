package com.example.controller;

import com.example.Utils.MD5;
import com.example.domain.FlightEntity;
import com.example.domain.UserEntity;
import com.example.repository.UserRepository;
import com.example.service.FlightService;
import com.example.service.TicketService;
import com.example.service.UserService;
import com.sun.tools.javac.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.AttributeOverride;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 * Created by iam24 on 17/3/30.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    TicketService ticketService;

    @Autowired
    FlightService flightService;

    /**
     * 编辑个人信息
     * @param old_password
     * @param new_password
     * @param session
     * @return
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public String edit(@RequestParam("old_password") String old_password,
                       @RequestParam("new_password") String new_password, HttpSession session){
        String result = userService.editPassword(old_password, new_password, session);
        return result;
    }

    /**
     * 订票
     * @param model
     * @param flight_number
     * @param session
     * @return
     */

    @RequestMapping(value = "/bookticket/{flight_number}", method = RequestMethod.GET)
    public String bookticket(Model model,@PathVariable("flight_number") long flight_number,
                             HttpSession session){
        String result = ticketService.bookTicket(flight_number, session);
        model.addAttribute("result", result);
        return "UserResult";
    }

    /**
     * 查看航班信息
     * @param model
     * @return
     */
    @RequestMapping(value = "/allflight",method = RequestMethod.GET)
    public String allflight(Model model){
        model.addAttribute("flights",flightService.findAllFlight());
        return "UserBookTicket";
    }

    /**
     * 退票
     * @param model
     * @param flight_number
     * @param session
     * @return
     */
    @RequestMapping(value = "/returnticket/{flight_number}", method = RequestMethod.GET)
    public String returnticket(Model model,@PathVariable("flight_number")long flight_number, HttpSession session){
        UserEntity user = (UserEntity)session.getAttribute("user");
        String name = user.getName();
        String result = ticketService.returnTicket(flight_number, name);
        model.addAttribute("result",result);
        return "UserResult";
    }

    /**
     * 我的订单
     * @param model
     * @param session
     * @return
     */
    @RequestMapping(value = "/myticket", method = RequestMethod.GET)
    public String myticket(Model model, HttpSession session){
        model.addAttribute("tickets", ticketService.myticket(session));
        return "UserTickets";
    }
}