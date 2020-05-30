package com.huiaong.pikachu.item.impl.category.service;


import com.alibaba.dubbo.config.annotation.Service;
import com.google.common.base.Throwables;
import com.huiaong.pikachu.common.pager.Paging;
import com.huiaong.pikachu.common.response.Response;
import com.huiaong.pikachu.item.category.criteria.PikaCategoryCriteria;
import com.huiaong.pikachu.item.category.model.PikaCategory;
import com.huiaong.pikachu.item.category.service.PikaCategoryReadService;
import com.huiaong.pikachu.item.impl.category.dao.PikaCategoryDao;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@Service(timeout = 3000)
public class PikaCategoryReadServiceImpl implements PikaCategoryReadService {
    private final PikaCategoryDao pikaCategoryDao;

    @Override
    public Response<Paging<PikaCategory>> paging(PikaCategoryCriteria criteria) {
        try {
            return Response.ok(pikaCategoryDao.paging(criteria.toMap()));
        } catch (Exception e) {
            log.error("find category by criteria:{} fail, cause={}", criteria, Throwables.getStackTraceAsString(e));
            return Response.fail("goods.find.fail");
        }
    }
}
