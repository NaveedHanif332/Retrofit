package com.example.retrofit_demo;

public class ReadingModes {
    private Boolean text;
    private Boolean image;

    public ReadingModes(Boolean text, Boolean image) {
        this.text = text;
        this.image = image;
    }

    public Boolean getText() {
        return text;
    }

    public void setText(Boolean text) {
        this.text = text;
    }

    public Boolean getImage() {
        return image;
    }

    public void setImage(Boolean image) {
        this.image = image;
    }
}