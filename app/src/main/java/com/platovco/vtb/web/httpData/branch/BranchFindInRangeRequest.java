package com.platovco.vtb.web.httpData.branch;

public class BranchFindInRangeRequest {
    private Double xLeft;
    private Double yUp;
    private Double xRight;
    private Double yDown;

    public BranchFindInRangeRequest(Double xLeft, Double yUp, Double xRight, Double yDown) {
        this.xLeft = xLeft;
        this.yUp = yUp;
        this.xRight = xRight;
        this.yDown = yDown;
    }

    public Double getxLeft() {
        return xLeft;
    }

    public void setxLeft(Double xLeft) {
        this.xLeft = xLeft;
    }

    public Double getyUp() {
        return yUp;
    }

    public void setyUp(Double yUp) {
        this.yUp = yUp;
    }

    public Double getxRight() {
        return xRight;
    }

    public void setxRight(Double xRight) {
        this.xRight = xRight;
    }

    public Double getyDown() {
        return yDown;
    }

    public void setyDown(Double yDown) {
        this.yDown = yDown;
    }

    public String toString() {
        return String.format("%f:%f %f:%f", xLeft, yUp, xRight, yDown);
    }
}