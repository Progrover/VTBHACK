package com.platovco.vtb.models;

import java.io.Serializable;
import java.util.Map;

public class MarkBranch implements Serializable {
    private Branch branch;
    private Double longitude;
    private Double latitude;

    public MarkBranch(Branch branch) {
        this.branch = branch;
        this.longitude = branch.getY();
        this.latitude = branch.getX();
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
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
