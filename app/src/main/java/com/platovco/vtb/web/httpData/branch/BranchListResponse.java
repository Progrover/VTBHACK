package com.platovco.vtb.web.httpData.branch;

import com.platovco.vtb.models.Branch;

import java.util.List;

public class BranchListResponse {
    private List<Branch> branches;

    public BranchListResponse(List<Branch> branches) {
        this.branches = branches;
    }

    public List<Branch> getBranches() {
        return branches;
    }
}

