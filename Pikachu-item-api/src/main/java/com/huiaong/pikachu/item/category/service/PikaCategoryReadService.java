package com.huiaong.pikachu.item.category.service;

import com.huiaong.pikachu.common.pager.Paging;
import com.huiaong.pikachu.common.response.Response;
import com.huiaong.pikachu.item.category.criteria.PikaCategoryCriteria;
import com.huiaong.pikachu.item.category.model.PikaCategory;

public interface PikaCategoryReadService {
    Response<Paging<PikaCategory>> paging(PikaCategoryCriteria criteria);
}
