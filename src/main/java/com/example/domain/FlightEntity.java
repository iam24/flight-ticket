package com.example.domain;

import javax.persistence.*;

/**
 * Created by iam24 on 17/3/30.
 */
//航线信息表
@Entity
@Table(name = "flight_entity", uniqueConstraints = {@UniqueConstraint(columnNames = "flight")})
public class FlightEntity {
    @Id
    @GeneratedValue
    private long id;
    private long plane_id;
    private String destination;
    private long booked_ticket;
    private long remain_ticket;
    private long flight ;

    public long getFlight() {
        return flight;
    }

    public void setFlight(long flight) {
        this.flight = flight;
    }


    public long getId() {

        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPlane_id() {
        return plane_id;
    }

    public void setPlane_id(long plane_id) {
        this.plane_id = plane_id;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public long getBooked_ticket() {
        return booked_ticket;
    }

    public void setBooked_ticket(long booked_ticket) {
        this.booked_ticket = booked_ticket;
    }

    public long getRemain_ticket() {
        return remain_ticket;
    }

    public void setRemain_ticket(long remain_ticket) {
        this.remain_ticket = remain_ticket;
    }

    protected FlightEntity() {}

    public FlightEntity(long flight,long plane_id, String destination, long booked_ticket, long remain_ticket) {
        this.plane_id = plane_id;
        this.destination = destination;
        this.booked_ticket = booked_ticket;
        this.remain_ticket = remain_ticket;
        this.flight = flight;
    }
}
