package com.example.repository;

import com.example.domain.FlightEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;


/**
 * Created by iam24 on 17/4/6.
 */
@Repository
public interface FlightRepository  extends CrudRepository<FlightEntity, Long> {
    FlightEntity findByFlight(long flight);

    @Modifying
    @Transactional
    @Query("update com.example.domain.FlightEntity f SET f.remain_ticket = f.remain_ticket + 1, f.booked_ticket = f.booked_ticket - 1 where f.flight = ?1")
    void updateReturnTicket(long flight);

    @Modifying
    @Transactional
    @Query("update com.example.domain.FlightEntity f SET f.remain_ticket = f.remain_ticket - 1, f.booked_ticket = f.booked_ticket + 1 where f.flight = ?1")
    void updateBookTicket(long flight);
    ArrayList<FlightEntity> findAll();
}
