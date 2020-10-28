package com.example.demo.enums;

import java.util.Arrays;

public enum userStatus {
    no("1", "停用状态"),
    yes("0", "使用状态");

    private String value;
    private String name;

    userStatus(String value, String name) {
        this.value = value;
        this.name = name;
    }

    public String getValue() {
        return this.value;
    }

    public String getName() {
        return this.name;
    }

    public boolean toBoolean() {
        return this == userStatus.yes ? true : false;
    }

    public static String booleanToString(boolean value) {
        return value ? userStatus.yes.getValue() : userStatus.no.getValue();
    }

    public static userStatus getByValue(String value) {
        return Arrays.stream(userStatus.values()).filter(c -> c.getValue().equals(value)).findFirst().orElse(null);
    }
}
