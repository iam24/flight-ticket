package com.example.service;

import com.example.domain.FlightEntity;
import com.example.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * Created by iam24 on 17/4/10.
 */
@Service
public class FlightService {
    @Autowired
    FlightRepository flightRepository;

    public ArrayList<FlightEntity> findAllFlight(){
        return flightRepository.findAll();
    }

//    public String addflight(long flight_number,String destination, long plane_id,String remain_ticket,String booked_ticket){
//        if (flightRepository.findByFlight(flight_number) != null)
//            return "添加失败! 航班编号重复!";
//        FlightEntity flightEntity = new (flight_number, plane_id, destination, booked_ticket, remain_ticket);
//        flightRepository.save(flightEntity);
//        return "添加成功!";
//    }
}
