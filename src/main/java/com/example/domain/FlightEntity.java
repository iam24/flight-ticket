package com.example.domain;

import javax.persistence.*;

/**
 * Created by iam24 on 17/3/30.
 */
@Entity
public class FlightEntity {
    @Id
    @GeneratedValue
    private int id;
    private int plane_id;
    private String destination;
    private int booked_ticket;
    private int remain_ticket;


    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPlane_id() {
        return plane_id;
    }

    public void setPlane_id(int plane_id) {
        this.plane_id = plane_id;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getBooked_ticket() {
        return booked_ticket;
    }

    public void setBooked_ticket(int booked_ticket) {
        this.booked_ticket = booked_ticket;
    }

    public int getRemain_ticket() {
        return remain_ticket;
    }

    public void setRemain_ticket(int remain_ticket) {
        this.remain_ticket = remain_ticket;
    }

    protected FlightEntity() {}

    public FlightEntity(int plane_id, String destination, int booked_ticket, int remain_ticket) {
        this.plane_id = plane_id;
        this.destination = destination;
        this.booked_ticket = booked_ticket;
        this.remain_ticket = remain_ticket;
    }
}
