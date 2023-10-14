package com.platovco.vtb.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.DayOfWeek;
import java.util.Objects;
import java.util.SortedMap;

public class Branch {
    @JsonProperty("id") private Long id;
    @JsonProperty("name") private String name;
    @JsonProperty("address") private String address;
    @JsonProperty("dailyLoad") private SortedMap<DayOfWeek, DaySchedule> dailyLoad;
    @JsonProperty("grade") private Double grade;
    @JsonProperty("amountOfReviews") private Long amountOfReviews;
    @JsonProperty("load") private Long load;
    @JsonProperty("x") private Double x;
    @JsonProperty("y") private Double y;

    public Branch() {
    }

    public Branch(Long id,
                  String name,
                  String address,
                  SortedMap<DayOfWeek, DaySchedule> dailyLoad,
                  Double grade,
                  Long amountOfReviews,
                  Long load,
                  Double x,
                  Double y) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.dailyLoad = dailyLoad;
        this.grade = grade;
        this.amountOfReviews = amountOfReviews;
        this.load = load;
        this.x = x;
        this.y = y;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public SortedMap<DayOfWeek, DaySchedule> getDailyLoad() {
        return dailyLoad;
    }

    public void setDailyLoad(SortedMap<DayOfWeek, DaySchedule> dailyLoad) {
        this.dailyLoad = dailyLoad;
    }

    public Double getGrade() {
        return grade;
    }

    public void setGrade(Double grade) {
        this.grade = grade;
    }

    public Long getAmountOfReviews() {
        return amountOfReviews;
    }

    public void setAmountOfReviews(Long amountOfReviews) {
        this.amountOfReviews = amountOfReviews;
    }

    public Long getLoad() {
        return load;
    }

    public void setLoad(Long load) {
        this.load = load;
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Branch{" +
                "id=" + id +
                '}';
    }
}
