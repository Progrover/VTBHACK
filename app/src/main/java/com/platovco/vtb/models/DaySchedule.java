package com.platovco.vtb.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class DaySchedule {
    @JsonProperty("id") private Long id;
    @JsonProperty("hours") private String hours;
    @JsonProperty("works") private Boolean works;
    @JsonProperty("hourlyLoads") private List<HourlyLoad> hourlyLoads;

    public DaySchedule() {
    }

    public DaySchedule(Long id, String hours, Boolean works, List<HourlyLoad> hourlyLoads) {
        this.id = id;
        this.hours = hours;
        this.works = works;
        this.hourlyLoads = hourlyLoads;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public Boolean getWorks() {
        return works;
    }

    public void setWorks(Boolean works) {
        this.works = works;
    }

    public List<HourlyLoad> getHourlyLoads() {
        return hourlyLoads;
    }

    public void setHourlyLoads(List<HourlyLoad> hourlyLoads) {
        this.hourlyLoads = hourlyLoads;
    }

    @Override
    public String toString() {
        return "DaySchedule{" +
                "id=" + id +
                '}';
    }
}
