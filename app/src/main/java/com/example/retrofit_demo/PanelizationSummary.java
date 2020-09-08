package com.example.retrofit_demo;

public class PanelizationSummary {
    private Boolean containsEpubBubbles;
    private Boolean containsImageBubbles;

    public PanelizationSummary(Boolean containsEpubBubbles, Boolean containsImageBubbles) {
        this.containsEpubBubbles = containsEpubBubbles;
        this.containsImageBubbles = containsImageBubbles;
    }

    public Boolean getContainsEpubBubbles() {
        return containsEpubBubbles;
    }

    public void setContainsEpubBubbles(Boolean containsEpubBubbles) {
        this.containsEpubBubbles = containsEpubBubbles;
    }

    public Boolean getContainsImageBubbles() {
        return containsImageBubbles;
    }

    public void setContainsImageBubbles(Boolean containsImageBubbles) {
        this.containsImageBubbles = containsImageBubbles;
    }
}
