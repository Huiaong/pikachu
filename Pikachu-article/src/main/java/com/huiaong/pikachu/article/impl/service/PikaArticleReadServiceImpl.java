package com.huiaong.pikachu.article.impl.service;

import com.google.common.base.Throwables;
import com.huiaong.pikachu.article.criteria.PikaArticleCriteria;
import com.huiaong.pikachu.article.impl.dao.PikaAritcleDao;
import com.huiaong.pikachu.article.model.PikaArticle;
import com.huiaong.pikachu.article.service.PikaArticleReadService;
import com.huiaong.pikachu.common.pager.Paging;
import com.huiaong.pikachu.common.response.Response;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@AllArgsConstructor
@com.alibaba.dubbo.config.annotation.Service
public class PikaArticleReadServiceImpl implements PikaArticleReadService {
    private final PikaAritcleDao pikaAritcleDao;
    private final RedisTemplate<String, Object> redisTemplate;

    private final Long ARTICLE_TIME_OUT = 1000 * 60 * 60 * 8L;

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
        String key = "article:" + id;
        PikaArticle article;
        try {
            if (Objects.isNull(article = (PikaArticle) redisTemplate.opsForValue().get(key))) {
                article = pikaAritcleDao.findById(id);
                redisTemplate.opsForValue().set(key, article, ARTICLE_TIME_OUT, TimeUnit.MILLISECONDS);
            }
            return Response.ok(article);
        } catch (Exception e) {
            log.error("find article by id:{} fail, cause={}", id, Throwables.getStackTraceAsString(e));
            return Response.fail("article.find.fail");
        }
    }
}
