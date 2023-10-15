package com.platovco.vtb.web.httpData.atm;

import com.platovco.vtb.models.ATM;

public class ATMGetResponse {
    private ATM atm;

    public ATMGetResponse(ATM atm) {
        this.atm = atm;
    }

    public ATM getAtm() {
        return atm;
    }
}