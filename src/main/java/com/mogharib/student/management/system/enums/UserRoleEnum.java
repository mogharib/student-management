package com.mogharib.student.management.system.enums;

public enum UserRoleEnum {
    USER("User"),
    ADMIN("Admin");
    public final String value;

    UserRoleEnum(String value) {
        this.value = value;
    }
}