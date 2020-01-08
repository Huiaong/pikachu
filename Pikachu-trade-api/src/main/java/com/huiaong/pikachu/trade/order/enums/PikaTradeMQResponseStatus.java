package com.huiaong.pikachu.trade.order.enums;

import lombok.AllArgsConstructor;

import java.util.Objects;

@AllArgsConstructor
public enum PikaTradeMQResponseStatus {
    WAIT_SEND(0, "待投递"),
    HAS_SEND(1, "投递完成"),
    FAIL_SEND(2, "投递失败");

    private Integer value;

    private String desc;

    public static PikaTradeMQResponseStatus fromValue(int value) {
        for (PikaTradeMQResponseStatus status : PikaTradeMQResponseStatus.values()) {
            if (Objects.equals(status.value, value)) {
                return status;
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
