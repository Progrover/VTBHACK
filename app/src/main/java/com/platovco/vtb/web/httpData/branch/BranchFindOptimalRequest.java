package com.platovco.vtb.web.httpData.branch;

public class BranchFindOptimalRequest {
    private Double xMe;
    private Double yMe;

    public String toString() {
        return String.format("%f:%f", xMe, yMe);
    }

    public BranchFindOptimalRequest(Double xMe, Double yMe) {
        this.xMe = xMe;
        this.yMe = yMe;
    }

    public Double getxMe() {
        return xMe;
    }

    public Double getyMe() {
        return yMe;
    }
}