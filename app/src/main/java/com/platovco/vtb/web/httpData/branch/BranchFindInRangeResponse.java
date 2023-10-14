package com.platovco.vtb.web.httpData.branch;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.platovco.vtb.models.Branch;

import java.util.List;

public class BranchFindInRangeResponse {
    private List<Branch> branches;

    @JsonCreator
    public BranchFindInRangeResponse(@JsonProperty("branches") List<Branch> branches) {
        this.branches = branches;
    }

    public List<Branch> getBranches() {
        return branches;
    }

    public void setBranches(List<Branch> branches) {
        this.branches = branches;
    }
}