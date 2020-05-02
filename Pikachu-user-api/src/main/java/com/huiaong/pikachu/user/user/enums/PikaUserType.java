package com.huiaong.pikachu.user.user.enums;

import lombok.AllArgsConstructor;

import java.util.Objects;

@AllArgsConstructor
public enum PikaUserType {

    ADMIN(0, "管理员"),
    SALESMAN(1, "业务员"),
    CUSTOMER(2, "用户");

    private final Integer value;
    private final String desc;

    public static PikaUserType from(int value) {
        for (PikaUserType range : PikaUserType.values()) {
            if (Objects.equals(range.value, value)) {
                return range;
            }
        }
        throw new IllegalArgumentException("illegal user type: " + value);
    }

    public Integer value() {
        return this.value;
    }

    public String getDesc() {
        return this.desc;
    }
}
