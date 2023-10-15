package com.platovco.vtb.models;

public class HourlyLoad {
    private Long id;
    private Long hour;
    private Long load;

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
}
