package com.example.domain;

import javax.persistence.*;

/**
 * Created by iam24 on 17/3/30.
 */
//飞机票表
@Entity
public class TicketEntity {
    @Id
    @GeneratedValue
    private long id;
    private long seat_number;
    private String name;
    private long flight;

    public long getFlight() {
        return flight;
    }

    public void setFlight(long flight) {
        this.flight = flight;
    }

    public long  getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getSeat_number() {
        return seat_number;
    }

    public void setSeat_number(int seat_number) {
        this.seat_number = seat_number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    protected TicketEntity() {}

    public TicketEntity(String name, long flight, long seat_number) {
        this.seat_number = seat_number;
        this.name = name;
        this.flight= flight;
    }
}
