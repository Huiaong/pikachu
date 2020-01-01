package com.huiaong.pikachu.common.pager;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.huiaong.pikachu.common.criteria.Criteria;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Map;

@Data
@EqualsAndHashCode(callSuper = true)
public class PagingCriteria extends Criteria implements Serializable {

    @JsonIgnore
    private Integer pageNo = 1;
    @JsonIgnore
    private Integer pageSize = 20;
    @JsonIgnore
    private Boolean hasNext = true;

    public PagingCriteria() {
    }

    @JsonIgnore
    public Boolean hasNext() {
        return this.hasNext;
    }

    public void nextPage() {
        if (this.pageNo == null) {
            this.pageNo = 1;
        }

        this.pageNo = this.pageNo + 1;
    }

    public Integer getLimit() {
        PageInfo pageInfo = new PageInfo(this.pageNo, this.pageSize);
        return pageInfo.getLimit();
    }

    public Integer getOffset() {
        PageInfo pageInfo = new PageInfo(this.pageNo, this.pageSize);
        return pageInfo.getOffset();
    }

    public Map<String, Object> toMap() {
        this.formatDate();
        return super.toMap();
    }

    protected void formatDate() {
    }

    protected boolean canEqual(Object other) {
        return other instanceof PagingCriteria;
    }

}
