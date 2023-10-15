package com.platovco.vtb.web.httpData.atm;

public class ATMGetRequest {
    private Long id;

    public ATMGetRequest(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}