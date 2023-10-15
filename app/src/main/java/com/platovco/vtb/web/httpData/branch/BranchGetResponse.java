package com.platovco.vtb.web.httpData.branch;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.platovco.vtb.models.Branch;

public class BranchGetResponse {
    private Branch branch;

    @JsonCreator
    public BranchGetResponse(@JsonProperty("branch") Branch branch) {
        this.branch = branch;
    }

    public Branch getBranch() {
        return branch;
    }
}