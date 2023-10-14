package com.platovco.vtb.web.httpData.branch;

import com.platovco.vtb.models.Branch;

public class BranchGetResponse {
    private Branch branch;

    public BranchGetResponse(Branch branch) {
        this.branch = branch;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }
}