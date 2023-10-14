package com.platovco.vtb.models;

import android.location.Location;

import com.yandex.mapkit.geometry.Point;

import java.io.Serializable;

public class CustomPoint extends Point implements Serializable {
    private double latitude;
    private double longitude;

    public CustomPoint(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
    public CustomPoint(Point point) {
        this.latitude = point.getLatitude();
        this.longitude = point.getLongitude();
    }
    public CustomPoint(Location location) {
        this.latitude = location.getLatitude();
        this.longitude = location.getLongitude();
    }

    public CustomPoint() {
    }

    public double getLatitude() {
        return this.latitude;
    }

    public double getLongitude() {
        return this.longitude;
    }
}
