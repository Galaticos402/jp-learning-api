package com.self.jplearning.enums;

public enum LearningStatusEnum {
    AGAIN(0),
    HARD(1),
    GOOD(2),
    EASY(3);

    private final int value;

    // Constructor (luôn là private)
    LearningStatusEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
