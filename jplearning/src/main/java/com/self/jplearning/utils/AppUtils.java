package com.self.jplearning.utils;


import lombok.Getter;

public class AppUtils {
    @Getter
    public enum RoleType {
        LEARNER("Learner");

        private final String value;

        RoleType(String value) {
            this.value = value;
        }
    }
}

