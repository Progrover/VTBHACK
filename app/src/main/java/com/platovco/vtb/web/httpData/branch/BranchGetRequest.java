package com.platovco.vtb.web.httpData.branch;

public class BranchGetRequest {
    private Long id;

    public BranchGetRequest(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}