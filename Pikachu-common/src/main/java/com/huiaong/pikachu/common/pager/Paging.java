package com.huiaong.pikachu.common.pager;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;

@AllArgsConstructor
@Data
public class Paging<T> implements Serializable {

    private Long total;

    private Collection<T> data;

    public Boolean isEmpty() {
        return Objects.equals(0L, this.total) || this.data == null || this.data.isEmpty();
    }

}
