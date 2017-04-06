package com.example.repository;

import com.example.domain.TicketEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by iam24 on 17/4/6.
 */
@Repository
public interface TicketRepository extends CrudRepository<TicketEntity, Long> {
    TicketEntity findByName(String name);
    TicketEntity findByflight_number(long flight_number);
}
