package com.huiaong.pikachu.trade.order.enums;

import lombok.AllArgsConstructor;

import java.util.Objects;

@AllArgsConstructor
public enum PikaPurchaseOrderTypes {

    RETAIL(1, "零售"),
    COMPANY_CONTRACT(2, "公司合同"),
    THIRD_CONTRACT(3, "第三方合同"),
    THIRD_PARTY_ORDER(4, "电商采购单");

    private Integer value;
    private String desc;

    public static PikaPurchaseOrderTypes from(int value) {
        for (PikaPurchaseOrderTypes range : PikaPurchaseOrderTypes.values()) {
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
