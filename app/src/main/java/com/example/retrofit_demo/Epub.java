package com.example.retrofit_demo;

public class Epub {
    public Epub(Boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    private Boolean isAvailable;

    public Boolean getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(Boolean isAvailable) {
        this.isAvailable = isAvailable;
    }
}
