package com.example.repository;

import com.example.domain.TicketEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * Created by iam24 on 17/4/6.
 */
@Repository
public interface TicketRepository extends CrudRepository<TicketEntity, Long> {
//    TicketEntity findByName(String name);
    TicketEntity findByFlightAndName(long flight, String name);
    ArrayList<TicketEntity> findAll();
    ArrayList<TicketEntity> findByName(String name);
    ArrayList<TicketEntity> findByFlight(long flight);
}
