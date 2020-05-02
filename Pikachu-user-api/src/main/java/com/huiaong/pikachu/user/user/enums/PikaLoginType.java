package com.huiaong.pikachu.user.user.enums;


import lombok.AllArgsConstructor;

import java.util.Objects;

@AllArgsConstructor
public enum PikaLoginType {
    NAME(1, "用户名"),
    EMAIL(2, "邮箱"),
    MOBILE(3, "手机号");

    private final Integer value;
    private final String desc;


    public static PikaLoginType from(int value) {
        for (PikaLoginType range : PikaLoginType.values()) {
            if (Objects.equals(range.value, value)) {
                return range;
            }
        }
        throw new IllegalArgumentException("illegal login type: " + value);
    }

    public Integer value() {
        return this.value;
    }

    public String getDesc() {
        return this.desc;
    }
}
