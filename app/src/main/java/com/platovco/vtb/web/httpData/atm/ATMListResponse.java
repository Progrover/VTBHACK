package com.platovco.vtb.web.httpData.atm;

import com.platovco.vtb.models.ATM;

import java.util.List;

public class ATMListResponse {
    private List<ATM> atms;

    public ATMListResponse(List<ATM> atms) {
        this.atms = atms;
    }

    public List<ATM> getAtms() {
        return atms;
    }
}

