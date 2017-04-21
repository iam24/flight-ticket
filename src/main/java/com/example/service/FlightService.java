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

    public ArrayList<FlightEntity> FindAllFlight(){
        return flightRepository.findAll();
    }

    public String AddFlight(FlightEntity flightEntity){
        if (flightRepository.findByFlight(flightEntity.getFlight()) != null)
            return "添加失败! 航班编号重复!";
        flightRepository.save(flightEntity);
        return "添加成功!";
    }
}
