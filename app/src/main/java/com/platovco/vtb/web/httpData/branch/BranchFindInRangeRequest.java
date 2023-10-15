package com.platovco.vtb.web.httpData.branch;

public class BranchFindInRangeRequest {
    private Double xLeft;
    private Double yUp;
    private Double xRight;
    private Double yDown;

    public String toString() {

        return String.format("%f:%f %f:%f", xLeft, yUp, xRight, yDown);
    }

    public BranchFindInRangeRequest(Double xLeft, Double yUp, Double xRight, Double yDown) {
        this.xLeft = xLeft;
        this.yUp = yUp;
        this.xRight = xRight;
        this.yDown = yDown;
    }

    public Double getxLeft() {
        return xLeft;
    }

    public Double getyUp() {
        return yUp;
    }

    public Double getxRight() {
        return xRight;
    }

    public Double getyDown() {
        return yDown;
    }
}