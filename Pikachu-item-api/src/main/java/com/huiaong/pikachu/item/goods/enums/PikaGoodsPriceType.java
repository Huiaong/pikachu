package com.huiaong.pikachu.item.goods.enums;

import lombok.AllArgsConstructor;

import java.util.Objects;

@AllArgsConstructor
public enum PikaGoodsPriceType {

    NORMAL(0, "正常价格"),
    DIRECTLY(1, "直降"),
    PROPORTION(2, "比例"),
    ;

    private Integer value;
    private String desc;

    public static PikaGoodsPriceType from(int value) {
        for (PikaGoodsPriceType status : PikaGoodsPriceType.values()) {
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
