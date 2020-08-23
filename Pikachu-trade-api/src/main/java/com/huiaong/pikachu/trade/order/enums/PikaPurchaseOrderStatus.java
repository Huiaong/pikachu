package com.huiaong.pikachu.trade.order.enums;

import lombok.AllArgsConstructor;

import java.util.Objects;

@AllArgsConstructor
public enum PikaPurchaseOrderStatus {
    WAIT_ACCEPT(0, "待客服确认"),
    ACCEPTED(1, "待计划"),
    PROCESSING(2, "处理中"),
    WAIT_CONFIRM(3, "待客户确认"),
    FINISH(4, "已完成"),
    BUYER_CANCEL(-1, "买家已取消"),
    SERVICE_USER_CANCEL(-2, "客服已取消"),
    REJECT_ACCEPT(-3, "已拒绝");

    private Integer value;
    private String desc;

    public static PikaPurchaseOrderStatus from(int value) {
        for (PikaPurchaseOrderStatus range : PikaPurchaseOrderStatus.values()) {
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
