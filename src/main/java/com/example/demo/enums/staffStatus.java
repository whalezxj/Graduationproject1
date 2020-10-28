package com.example.demo.enums;

import java.util.Arrays;

public enum staffStatus {
    no("1", "离职状态"),
    yes("0", "在职状态");

    private String value;
    private String name;

    staffStatus(String value, String name) {
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
        return this == staffStatus.yes ? true : false;
    }

    public static String booleanToString(boolean value) {
        return value ? staffStatus.yes.getValue() : staffStatus.no.getValue();
    }

    public static staffStatus getByValue(String value) {
        return Arrays.stream(staffStatus.values()).filter(c -> c.getValue().equals(value)).findFirst().orElse(null);
    }
}
