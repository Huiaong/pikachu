package com.huiaong.pikachu.item.goods.enums;

import lombok.AllArgsConstructor;

import java.util.Objects;

@AllArgsConstructor
public enum PikaGoodsStatus {

    DELETE(-1, "删除"),
    SOLD_OUT(0, "下架"),
    CREATED(1, "创建"),
    ON_SALE(2, "在售"),
    ;

    private Integer value;
    private String desc;

    public static PikaGoodsStatus from(int value) {
        for (PikaGoodsStatus status : PikaGoodsStatus.values()) {
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
