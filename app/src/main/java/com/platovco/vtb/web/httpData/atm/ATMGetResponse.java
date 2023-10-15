package com.platovco.vtb.web.httpData.atm;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.platovco.vtb.models.ATM;

public class ATMGetResponse {
    private ATM atm;

    @JsonCreator
    public ATMGetResponse(@JsonProperty("atm") ATM atm) {
        this.atm = atm;
    }

    public ATM getAtm() {
        return atm;
    }
}