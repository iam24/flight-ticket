package com.example.service;

import com.example.domain.TicketEntity;
import com.example.domain.UserEntity;
import com.example.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

/**
 * Created by iam24 on 17/4/6.
 */
@Service
public class TicketService {
    @Autowired
    TicketRepository ticketRepository;
    public String bookTicket(long flight_number, HttpSession session){
        UserEntity userEntity = (UserEntity)  session.getAttribute("user");
        if (userEntity == null) {return "请先登录!";}
        TicketEntity ticketEntity = ticketRepository.findByflight_number(flight_number);
        if (ticketEntity.get)
    }
}
