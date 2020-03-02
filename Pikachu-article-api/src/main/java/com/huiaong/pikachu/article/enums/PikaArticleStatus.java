package com.huiaong.pikachu.article.enums;

import lombok.AllArgsConstructor;

import java.util.Objects;

@AllArgsConstructor
public enum PikaArticleStatus {

    PUBLISHED(1, "已发布"),
    UNPUBLISHED(0, "未发布"),
    UNUSED(-1, "已停用"),
    DELETED(-2, "删除");
    ;

    private Integer value;
    private String desc;

    public static PikaArticleStatus from(int value) {
        for (PikaArticleStatus range : PikaArticleStatus.values()) {
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
