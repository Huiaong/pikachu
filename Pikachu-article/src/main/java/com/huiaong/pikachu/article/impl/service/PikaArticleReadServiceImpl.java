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
import org.redisson.api.RBloomFilter;
import org.redisson.api.RBucket;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@AllArgsConstructor
@com.alibaba.dubbo.config.annotation.Service
public class PikaArticleReadServiceImpl implements PikaArticleReadService {
    private final PikaAritcleDao pikaAritcleDao;
    private final RedissonClient redissonClient;

    private final static Random random = new Random();
    private final Long ARTICLE_TIME_OUT = 1000 * 60 * 60 * 8L;
    private final String ARTICLE_BF = "ARTICLE:BF";

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
        String key = "ARTICLE:" + id;
        PikaArticle article = new PikaArticle();
        try {
            RBloomFilter<Long> articleBF = redissonClient.getBloomFilter(ARTICLE_BF);
            RBucket<PikaArticle> rBucket = redissonClient.getBucket(key);
            //布隆过滤器 防止 缓存穿透
            if (articleBF.contains(id) && Objects.isNull(article = rBucket.get())) {
                //分部署锁  防止 缓存击穿
                RLock rLock = redissonClient.getLock("RLOCK:" + key);
                rLock.lock(15, TimeUnit.SECONDS);
                try {
                    if (Objects.isNull(article = rBucket.get())) {
                        article = pikaAritcleDao.findById(id);
                        //随机过期时间 防止 缓存雪崩
                        int randomMicroseconds = random.nextInt(1000 * 60 * 5) + 1;
                        rBucket.set(article, ARTICLE_TIME_OUT + randomMicroseconds, TimeUnit.MICROSECONDS);
                    }
                } finally {
                    rLock.unlock();
                }
            }
            return Response.ok(article);
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
