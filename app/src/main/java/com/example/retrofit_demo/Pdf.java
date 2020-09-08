package com.example.retrofit_demo;

public class Pdf {
    private Boolean isAvailable;
    private String acsTokenLink;

    public Pdf(Boolean isAvailable, String acsTokenLink) {
        this.isAvailable = isAvailable;
        this.acsTokenLink = acsTokenLink;
    }

    public Boolean getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(Boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public String getAcsTokenLink() {
        return acsTokenLink;
    }

    public void setAcsTokenLink(String acsTokenLink) {
        this.acsTokenLink = acsTokenLink;
    }
}
