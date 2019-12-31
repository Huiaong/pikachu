package com.huiaong.pikachu.common.pager;

import com.google.common.base.MoreObjects;
import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import lombok.Data;

import java.util.Map;

@Data
public class PageInfo {
    public static final String LIMIT = "limit";
    public static final String OFFSET = "offset";
    private Integer offset;
    private Integer limit;

    public PageInfo() {
    }

    public static PageInfo of(Integer pageNo, Integer size) {
        return new PageInfo(pageNo, size);
    }

    public PageInfo(Integer pageNo, Integer size) {
        pageNo = MoreObjects.firstNonNull(pageNo, 1);
        size = MoreObjects.firstNonNull(size, 20);
        this.limit = size > 0 ? size : 20;
        this.offset = (pageNo - 1) * size;
        this.offset = this.offset > 0 ? this.offset : 0;
    }

    public Map<String, Object> toMap() {
        return this.toMap((String)null, (String)null);
    }

    public Map<String, Object> toMap(String offset, String limit) {
        Map<String, Object> param = Maps.newHashMapWithExpectedSize(2);
        param.put(Strings.isNullOrEmpty(offset) ? "offset" : offset, this.offset);
        param.put(Strings.isNullOrEmpty(limit) ? "limit" : limit, this.limit);
        return param;
    }
}
