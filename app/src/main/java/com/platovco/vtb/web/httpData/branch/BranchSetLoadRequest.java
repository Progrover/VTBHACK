package com.platovco.vtb.web.httpData.branch;

public class BranchSetLoadRequest {
    private String name;
    private Long load;

    public BranchSetLoadRequest(String name, Long load) {
        this.name = name;
        this.load = load;
    }

    public String getName() {
        return name;
    }

    public Long getLoad() {
        return load;
    }
}