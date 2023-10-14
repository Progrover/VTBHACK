package com.platovco.vtb.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HourlyLoad {
    @JsonProperty("id") private Long id;
    @JsonProperty("hour") private Long hour;
    @JsonProperty("load") private Long load;

    public HourlyLoad() {
    }

    public HourlyLoad(Long id, Long hour, Long load) {
        this.id = id;
        this.hour = hour;
        this.load = load;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getHour() {
        return hour;
    }

    public void setHour(Long hour) {
        this.hour = hour;
    }

    public Long getLoad() {
        return load;
    }

    public void setLoad(Long load) {
        this.load = load;
    }

    @Override
    public String toString() {
        return "HourlyLoad{" +
                "id=" + id +
                '}';
    }
}
