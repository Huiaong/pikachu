package com.huiaong.pikachu.log.order.enums;

import lombok.AllArgsConstructor;

import java.util.Objects;

@AllArgsConstructor
public enum PikaPurchaseOrderOperationLogType {

    CREATED(1, "创建"),
    ;

    private Integer value;
    private String desc;

    public static PikaPurchaseOrderOperationLogType from(int value) {
        for (PikaPurchaseOrderOperationLogType range : PikaPurchaseOrderOperationLogType.values()) {
            if (Objects.equals(range.value, value)) {
                return range;
            }
        }
        return null;
    }

    public Integer value() {
        return this.value;
    }

    public String getDesc() {
        return this.desc;
    }

}
