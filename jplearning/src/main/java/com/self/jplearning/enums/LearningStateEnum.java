package com.self.jplearning.enums;

public enum LearningStateEnum {
    NEW("NEW"),
    LEARNING("LEARNING"),
    REVIEW("REVIEW"),
    RELEARNING("RELEARNING");

    private final String value;

    // Constructor (luôn là private)
    LearningStateEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
