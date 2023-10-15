package com.platovco.vtb.web.httpData.atm;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.platovco.vtb.models.ATM;

import java.util.List;

public class ATMListResponse {
    private List<ATM> atms;

    @JsonCreator
    public ATMListResponse(@JsonProperty("atms") List<ATM> atms) {
        this.atms = atms;
    }

    public List<ATM> getAtms() {
        return atms;
    }
}

