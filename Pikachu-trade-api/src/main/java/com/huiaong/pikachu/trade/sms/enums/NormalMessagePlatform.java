package com.huiaong.pikachu.trade.sms.enums;

import lombok.AllArgsConstructor;

import java.util.Objects;

@AllArgsConstructor
public enum NormalMessagePlatform {

    PC(1, "站内信"),
    Android(2, "Android消息推送"),
    IOS(3, "IOS消息推送"),
    SMS(4, "手机短信");

    private Integer value;

    private String desc;

    public static NormalMessagePlatform fromValue(int value) {
        for (NormalMessagePlatform enumm : NormalMessagePlatform.values()) {
            if (Objects.equals(enumm.value, value)) {
                return enumm;
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
