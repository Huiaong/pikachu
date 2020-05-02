package com.huiaong.pikachu.user.user.enums;

import lombok.AllArgsConstructor;

import java.util.Objects;

@AllArgsConstructor
public enum PikaUserStatus {

    NORMAL(1, "正常"),
    LOCKED(-1, "锁定"),
    FREEZE(-2, "冻结"),
    DELETED(-3, "删除");

    private final Integer value;
    private final String desc;

    public static PikaUserStatus from(int value) {
        for (PikaUserStatus range : PikaUserStatus.values()) {
            if (Objects.equals(range.value, value)) {
                return range;
            }
        }
        throw new IllegalArgumentException("illegal user status: " + value);
    }

    public Integer value() {
        return this.value;
    }

    public String getDesc() {
        return this.desc;
    }
}
