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
}
