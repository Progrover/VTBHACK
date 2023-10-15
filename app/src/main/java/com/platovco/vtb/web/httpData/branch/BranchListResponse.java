package com.platovco.vtb.web.httpData.branch;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.platovco.vtb.models.Branch;

import java.util.List;

public class BranchListResponse {
    private List<Branch> branches;

    @JsonCreator
    public BranchListResponse(@JsonProperty("branches") List<Branch> branches) {
        this.branches = branches;
    }

    public List<Branch> getBranches() {
        return branches;
    }
}

