package com.example.controller;

import com.example.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.w3c.dom.html.HTMLAnchorElement;

import javax.servlet.http.HttpSession;

/**
 * Created by iam24 on 17/4/6.
 */
@Controller
public class TicketController {
   @Autowired
   TicketService ticketService;

   @RequestMapping(value = "/user/bookticket", method = RequestMethod.POST)
   @ResponseBody
    public String bookticket(@RequestParam("flight_number") long flight_number,
                             @RequestParam("seat_number") long seat_number,
                             HttpSession session){
       String result = ticketService.bookTicket(flight_number, seat_number, session);
       return result;
   }


   @RequestMapping(value = "/user/returnticket", method = RequestMethod.POST)
   @ResponseBody
   public String returnticket(@RequestParam("flight_number") long flight_number,
                              @RequestParam("name") String name){
      String result = ticketService.returnTicket(flight_number, name);
      return result;
   }
}

