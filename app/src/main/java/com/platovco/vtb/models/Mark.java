package com.platovco.vtb.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

public class Mark implements Serializable {
    private Double longitude;
    private Double latitude;
    private String pinUrl;

    public String registrationId;

    private Long toDate;

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    private Long duration;

    private Long price;
    private String event;

    private ArrayList<String> photosUrl = new ArrayList<>();
    private ArrayList<String> tags = new ArrayList<>();



    public ArrayList<String> getPhotosUrl() {
        return photosUrl;
    }

    public void setPhotosUrl(ArrayList<String> photosUrl) {
        this.photosUrl = photosUrl;
    }


    public ArrayList<String> getActiveUsers() {
        return activeUsers;
    }

    public void setActiveUsers(ArrayList<String> activeUsers) {
        this.activeUsers = activeUsers;
    }

    private ArrayList<String> activeUsers;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    private String uuid;
    private Long userCount;

    public String getFromTime() {
        return fromTime;
    }

    public void setFromTime(String fromTime) {
        this.fromTime = fromTime;
    }

    public String getToTime() {
        return toTime;
    }

    public void setToTime(String toTime) {
        this.toTime = toTime;
    }

    public String getCreatorID() {
        return creatorID;
    }

    public void setCreatorID(String creatorID) {
        this.creatorID = creatorID;
    }

    private String fromTime;
    private String toTime;
    private String creatorID;


    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    private String desc;

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

    public Long getToDate() {
        return toDate;
    }

    public void setToDate(Long toDate) {
        this.toDate = toDate;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public Long getUserCount() {
        return userCount;
    }

    public void setUserCount(Long userCount) {
        this.userCount = userCount;
    }

    public Mark(Double longitude, Double latitude, Long toDate,
                Long price, String event, String pinUrl, Long userCount, String desc,
                String fromTime, ArrayList<String> photosUrl, String uuid, ArrayList<String> tags, Long duration) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.toDate = toDate;
        this.price = price;
        this.event = event;
        this.pinUrl = pinUrl;
        this.userCount = userCount;
        this.desc = desc;
        this.fromTime = fromTime;
        this.photosUrl.clear();
        this.photosUrl.addAll(photosUrl);
        this.uuid = uuid;
        this.tags = tags;
        this.duration = duration;


    }

    public Mark(Map<String, Object> mapOfMark) {
        this.longitude = (Double) mapOfMark.get("longitude");
        this.latitude = (Double) mapOfMark.get("latitude");
        this.toDate = ((Double) mapOfMark.get("toDate")).longValue();
        this.price = ((Double) mapOfMark.get("price")).longValue();
        this.event = (String) mapOfMark.get("event");
        this.userCount = ((Double) mapOfMark.get("userCount")).longValue();
        this.desc = (String) mapOfMark.get("desc");
        this.fromTime = (String) mapOfMark.get("fromTime");
        this.toTime = (String) mapOfMark.get("toTime");
        this.creatorID = (String) mapOfMark.get("creatorID");
        this.uuid = (String) mapOfMark.get("$id");
        this.photosUrl = (ArrayList<String>) mapOfMark.get("photosUrl");
        this.pinUrl = (String) mapOfMark.get("pinUrl");
        this.activeUsers = (ArrayList<String>) mapOfMark.get("activeUsers");
        this.tags = (ArrayList<String>) mapOfMark.get("tags");
        this.duration = ((Double) mapOfMark.get("duration")).longValue();
    }

    public Mark() {}

    public String getPinUrl() {
        return pinUrl;
    }

    public void setPinUrl(String pinUrl) {
        this.pinUrl = pinUrl;
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }
}
