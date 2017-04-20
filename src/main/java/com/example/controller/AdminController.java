package com.example.controller;

import com.example.domain.FlightEntity;
import com.example.domain.UserEntity;
import com.example.service.FlightService;
import com.example.service.TicketService;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by iam24 on 17/4/11.
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    UserService userService;

    @Autowired
    TicketService ticketService;

    @Autowired
    FlightService flightService;
    @RequestMapping(value = "/alluser", method = RequestMethod.GET)
    public String alluser(Model model){
        model.addAttribute("users", userService.findAllUser());
        return "AllUser";
    }

    /**
     * 导入航班信息
     */

    @RequestMapping(value = "/importexcel", method = RequestMethod.GET)
    public String importexcel(){
        return "importexcel";
    }

    @RequestMapping(value = "/importexcel", method = RequestMethod.POST)
    public String importexcel(Model model,@RequestParam("file") MultipartFile file) throws IOException {
        String result = userService.importexcel(file);
        model.addAttribute("result",result);
        return "importexcel";
    }

    /**
     * 管理员退票
     * @param model
     * @param name
     * @param flight_number
     * @param session
     * @return
     */
    @RequestMapping(value = "/returnticket/{name}/{flight_number}", method = RequestMethod.GET)
    public String returnticket(Model model,@PathVariable("name") String name, @PathVariable("flight_number") long flight_number, HttpSession session){
        String result = ticketService.returnTicket(flight_number, name);
        model.addAttribute("result", result);
        return "AdminResult";
    }

    /**
     * 查看所有航班信息
     * @param model
     * @return
     */
    @RequestMapping(value = "/allflight",method = RequestMethod.GET)
    public String allflight(Model model){
        model.addAttribute("flights",flightService.findAllFlight());
        return "AdminIndex";

    }

    /**
     * 查看所有订单
     * @param model
     * @return
     */
    @RequestMapping(value = "alltickets", method = RequestMethod.GET)
    public String alltickets(Model model){
        model.addAttribute("tickets", ticketService.alltickets());
        return "alltickets";
    }

    /**
     * 删除用户
     * @param model
     * @param id
     * @param session
     * @return
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(Model model,@PathVariable("id") long id, HttpSession session){
        String result = userService.delete(id, session);
        model.addAttribute("result", result);
        return "AdminResult";
    }

    /**
     * 导出航班信息
     * @param model
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/downloadexcel",method = RequestMethod.GET)
    public String downloadexcel(Model model)throws IOException{
        String result = userService.downloadexcel();
        model.addAttribute("result", result);
        return "downloadexcel";
    }

//    @RequestMapping(value = "addflight", method = RequestMethod.POST)
//    public String addflight(Model model, @RequestParam("flight_number") long flight_number,
//                            @RequestParam("destination") String destination,
//                            @RequestParam("plane_id") long plane_id,
//                            @RequestParam("remain_ticket") long remain_ticket,
//                            @RequestParam("booked_ticket") long booked_ticket){
//        String result = flightService.addflight(flight_number,destination,plane_id,remain_ticket,booked_ticket);
//        model.addAttribute("result",result);
//        return "AdminResult";
//    }
}
