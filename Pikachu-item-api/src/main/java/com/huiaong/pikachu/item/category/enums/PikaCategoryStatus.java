package com.huiaong.pikachu.item.category.enums;

import lombok.AllArgsConstructor;

import java.util.Objects;

@AllArgsConstructor
public enum PikaCategoryStatus {

    DISABLED(-1, "禁用"),
    ENABLED(0, "启用"),
    ;

    private Integer value;
    private String desc;

    public static PikaCategoryStatus from(int value) {
        for (PikaCategoryStatus status : PikaCategoryStatus.values()) {
            if (Objects.equals(status.value, value)) {
                return status;
            }
        }
        return null;
    }

    public Integer value() {
        return this.value;
    }

    public String toString() {
        return this.desc;
    }
}
