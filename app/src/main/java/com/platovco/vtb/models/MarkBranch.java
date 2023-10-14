package com.platovco.vtb.models;

import java.io.Serializable;

public class MarkBranch implements Serializable {
    private Branch branch;
    private Double longitude;
    private Double latitude;

    public MarkBranch(Branch branch) {
        this.branch = branch;
        latitude = branch.getX();
        longitude = branch.getY();
    }

    public MarkBranch() {}

    public Branch getData() {
        return branch;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }
}
