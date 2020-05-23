package com.huiaong.pikachu.article.impl.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.google.common.base.Throwables;
import com.huiaong.pikachu.article.criteria.PikaArticleCriteria;
import com.huiaong.pikachu.article.impl.dao.PikaAritcleDao;
import com.huiaong.pikachu.article.model.PikaArticle;
import com.huiaong.pikachu.article.service.PikaArticleReadService;
import com.huiaong.pikachu.common.pager.Paging;
import com.huiaong.pikachu.common.response.Response;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@AllArgsConstructor
@Service(timeout = 3000)
public class PikaArticleReadServiceImpl implements PikaArticleReadService {
    private final PikaAritcleDao pikaAritcleDao;

    @Override
    public Response<Paging<PikaArticle>> paging(PikaArticleCriteria criteria) {
        try {
            return Response.ok(pikaAritcleDao.paging(criteria.toMap()));
        } catch (Exception e) {
            log.error("paging article fail, criteria={}, cause={}", criteria, Throwables.getStackTraceAsString(e));
            return Response.fail("article.paging.fail");
        }
    }

    @Override
    public Response<PikaArticle> findById(Long id) {
        try {
            return Response.ok(pikaAritcleDao.findById(id));
        } catch (Exception e) {
            log.error("find article by id:{} fail, cause={}", id, Throwables.getStackTraceAsString(e));
            return Response.fail("article.find.fail");
        }
    }

    @Override
    public Response<List<Long>> listIds() {
        try {
            return Response.ok(pikaAritcleDao.listIds());
        } catch (Exception e) {
            log.error("list article ids fail, cause={}", Throwables.getStackTraceAsString(e));
            return Response.fail("article.ids.list.fail");
        }
    }
}
