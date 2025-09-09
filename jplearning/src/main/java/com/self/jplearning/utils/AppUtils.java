package com.self.jplearning.utils;


import lombok.Getter;

public class AppUtils {
    @Getter
    public enum RoleType {
        LEARNER(1);

        private final int value;

        RoleType(int value) {
            this.value = value;
        }
    }
}

