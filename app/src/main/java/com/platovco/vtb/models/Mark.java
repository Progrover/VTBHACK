package com.platovco.vtb.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

public class Mark implements Serializable {
    private Double longitude;
    private Double latitude;
    private String pinUrl;
    private String uuid;


    public Mark(Map<String, Object> mapOfMark) {
        this.longitude = (Double) mapOfMark.get("longitude");
        this.latitude = (Double) mapOfMark.get("latitude");
        this.uuid = (String) mapOfMark.get("$id");
        this.pinUrl = (String) mapOfMark.get("pinUrl");
    }

    public Mark(Double longitude, Double latitude, String uuid) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.uuid = uuid;
    }

    public Mark() {}

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

    public String getPinUrl() {
        return pinUrl;
    }

    public void setPinUrl(String pinUrl) {
        this.pinUrl = pinUrl;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
