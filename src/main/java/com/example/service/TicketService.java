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
import java.util.ArrayList;

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
    public String BookTicket(long flight_number, HttpSession session){
        UserEntity userEntity = (UserEntity)  session.getAttribute("user");
       // if (userEntity == null) {return "请先登录!";}
        FlightEntity flightEntity = flightRepository.findByFlight(flight_number);
        if (flightEntity == null) {return "没有此航班!";}
        if (flightEntity.getRemain_ticket() < 1){
            return "余票不足!";
        }
        if (ticketRepository.findByFlightAndName(flight_number,userEntity.getName()) != null){
            return "不能重复订票!";
        }
        ArrayList<TicketEntity> ticketEntityArrayList = ticketRepository.findByFlight(flight_number);
        int seat_number = 1;
        for (TicketEntity i : ticketEntityArrayList){
            if (i.getSeat_number() - seat_number > 1)
            {
                seat_number ++;
                break;
            }
            seat_number++;
        }
        flightRepository.updateBookTicket(flight_number);

//        flightEntity.setBooked_ticket(flightEntity.getBooked_ticket() + 1);
//        flightEntity.setRemain_ticket(flightEntity.getRemain_ticket() - 1);
//        flightRepository.save(flightEntity);

        TicketEntity ticketEntity = new TicketEntity(userEntity.getName(), flight_number,seat_number);
        ticketRepository.save(ticketEntity);
        return "订票成功!";
    }

    @Transactional
    public String ReturnTicket(long flight_number, String name) {
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

    public ArrayList<TicketEntity> AllTickets(){
        return ticketRepository.findAll();
    }

    public ArrayList<TicketEntity> MyTicket(HttpSession session){
        UserEntity userEntity = (UserEntity) session.getAttribute("user");
        String name = userEntity.getName();
        return ticketRepository.findByName(name);
    }

    @Transactional
    public String AdminBookTicket(TicketEntity ticketEntity){
        if (flightRepository.findByFlight(ticketEntity.getFlight()) == null){
            return "订票失败!没有此航班!";
        }
        if (ticketRepository.findByFlightAndName(ticketEntity.getFlight(),
                ticketEntity.getName()) != null){
            return "订票失败!不能为乘客重复订票!";
        }

        //分配座位  seat_number 若为0则系统分配, 若不为0贼检查座位是否已经被预订
        ArrayList<TicketEntity> ticketEntityArrayList = ticketRepository.findByFlight(ticketEntity.getFlight());
        if (ticketEntity.getSeat_number() != 0) {
            for (TicketEntity ticket : ticketEntityArrayList) {
                if (ticket.getSeat_number() == ticketEntity.getSeat_number()) {
                    return "订票失败!该座位已被预订!";
                }
            }
            ticketRepository.save(ticketEntity);
            flightRepository.updateBookTicket(ticketEntity.getFlight());
            return "订票成功!";
        }else
        {
            int seat_number = 1;
            for (TicketEntity i : ticketEntityArrayList){
                if (i.getSeat_number() - seat_number > 1)
                {
                    seat_number ++;
                    break;
                }
                seat_number++;
            }
            ticketEntity.setSeat_number(seat_number);
            ticketRepository.save(ticketEntity);
            flightRepository.updateBookTicket(ticketEntity.getFlight());
            return "订票成功!座位号为"+seat_number+"号";
        }
    }
}
