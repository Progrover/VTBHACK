package com.platovco.vtb.models;

import java.io.Serializable;

public class ATM implements Serializable {
    private Long id;
    private String address;
    private Double x;
    private Double y;

    public ATM() {
    }

    public ATM(Long id, String address, Double x, Double y) {
        this.id = id;
        this.address = address;
        this.x = x;
        this.y = y;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }
}
