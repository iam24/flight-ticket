package com.example.controller;

import com.example.domain.FlightEntity;
import com.example.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

/**
 * Created by iam24 on 17/4/10.
 */
@Controller
public class FlightController {
    @Autowired
    FlightService flightService;

    @RequestMapping(value = "/allflight",method = RequestMethod.GET)
    @ResponseBody
    public ArrayList<FlightEntity> allflight(){
        return flightService.findAllFlight();

    }

}
