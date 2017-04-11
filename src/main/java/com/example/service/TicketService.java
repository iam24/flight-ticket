package com.example.service;

import com.example.domain.FlightEntity;
import com.example.domain.TicketEntity;
import com.example.domain.UserEntity;
import com.example.repository.FlightRepository;
import com.example.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ejb.access.EjbAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;

/**
 * Created by iam24 on 17/4/6.
 */
@Service
public class TicketService {
    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    FlightRepository flightRepository;

    @Transactional
    public String bookTicket(long flight_number, long seat_number, HttpSession session){
        UserEntity userEntity = (UserEntity)  session.getAttribute("user");
       // if (userEntity == null) {return "请先登录!";}
        FlightEntity flightEntity = flightRepository.findByFlight(flight_number);
        if (flightEntity == null) {return "没有此航班!";}
        if (flightEntity.getRemain_ticket() < 1){
            return "余票不足!";
        }

        flightRepository.updateBookTicket(flight_number);

//        flightEntity.setBooked_ticket(flightEntity.getBooked_ticket() + 1);
//        flightEntity.setRemain_ticket(flightEntity.getRemain_ticket() - 1);
//        flightRepository.save(flightEntity);

        TicketEntity ticketEntity = new TicketEntity(userEntity.getName(), flight_number, seat_number);
        ticketRepository.save(ticketEntity);
        return "订票成功!";
    }

    @Transactional
    public String returnTicket(long flight_number, String name) {
        TicketEntity ticketEntity = ticketRepository.findByFlightAndName(flight_number, name);
        if (ticketEntity == null) {return "没有航票信息!";}
        ticketRepository.delete(ticketEntity);

        flightRepository.updateReturnTicket(flight_number);
//
//        FlightEntity flightEntity = flightRepository.findByFlight(flight_number);
//        flightEntity.setBooked_ticket(flightEntity.getBooked_ticket() - 1);
//        flightEntity.setRemain_ticket(flightEntity.getRemain_ticket() + 1);
//        flightRepository.save(flightEntity);

        return "退票成功!";
    }
}
