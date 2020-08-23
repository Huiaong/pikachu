package com.huiaong.pikachu.article.enums;

import lombok.AllArgsConstructor;

import java.util.Objects;

@AllArgsConstructor
public enum PikaArticleType {

    INFORMATION(1, "资讯"),
    NEWS(2, "新闻"),
    IMPORTANT_NOTICE(3, "重要通知"),
    CONTACT_US(4, "联系我们");

    private Integer value;
    private String desc;

    public static PikaArticleType from(int value) {
        for (PikaArticleType range : PikaArticleType.values()) {
            if (Objects.equals(range.value, value)) {
                return range;
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
