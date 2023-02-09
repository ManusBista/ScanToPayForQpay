package com.example.scantopayforqpay.Model;

public class channelLogin {
    private String appId;
    private String id;
    private String lat;
    private String lng;
    private String industryCode;
    private String industryReserve;

    public channelLogin(String appId, String id, String lat, String lng, String industryCode, String industryReserve) {
        this.appId = appId;
        this.id = id;
        this.lat = lat;
        this.lng = lng;
        this.industryCode = industryCode;
        this.industryReserve = industryReserve;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getIndustryCode() {
        return industryCode;
    }

    public void setIndustryCode(String industryCode) {
        this.industryCode = industryCode;
    }

    public String getIndustryReserve() {
        return industryReserve;
    }

    public void setIndustryReserve(String industryReserve) {
        this.industryReserve = industryReserve;
    }
}
