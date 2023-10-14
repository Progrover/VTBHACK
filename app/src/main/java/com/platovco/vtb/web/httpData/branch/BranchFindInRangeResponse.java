package com.platovco.vtb.web.httpData.branch;

import com.platovco.vtb.models.Branch;

import java.util.List;

public class BranchFindInRangeResponse {
    private List<Branch> branches;

    public BranchFindInRangeResponse(List<Branch> branches) {
        this.branches = branches;
    }

    public List<Branch> getBranches() {
        return branches;
    }

    public void setBranches(List<Branch> branches) {
        this.branches = branches;
    }
}