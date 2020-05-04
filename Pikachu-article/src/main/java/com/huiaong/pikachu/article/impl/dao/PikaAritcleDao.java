package com.huiaong.pikachu.article.impl.dao;

import com.huiaong.pikachu.article.constant.PikaArticleConstant;
import com.huiaong.pikachu.article.model.PikaArticle;
import com.huiaong.pikachu.common.mysql.dao.MyBatisDao;
import com.huiaong.pikachu.common.util.JsonMapper;
import lombok.AllArgsConstructor;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Repository
@AllArgsConstructor
public class PikaAritcleDao extends MyBatisDao<PikaArticle> {
    private final RedissonClient redissonClient;
    private final RedisTemplate<String, String> redisTemplate;
    private final Random random = new Random();

    public List<Long> listIds() {
        return this.sqlSession.selectList(this.sqlId("listIds"));
    }

    @Override
    public PikaArticle findById(Long id) {
        String key = "ARTICLE:" + id;

        PikaArticle article = new PikaArticle();
        RBloomFilter<Long> articleBF = redissonClient.getBloomFilter(PikaArticleConstant.ARTICLE_BF);

        //布隆过滤器 防止 缓存穿透
        if (articleBF.contains(id) && Objects.isNull(article = JsonMapper.nonDefaultMapper()
                .fromJson(redisTemplate.opsForValue().get(key), PikaArticle.class))) {
            //分部署锁  防止 缓存击穿
            RLock rLock = redissonClient.getLock("RLOCK:" + key);
            rLock.lock(15, TimeUnit.SECONDS);
            try {
                if (Objects.isNull(article = JsonMapper.nonDefaultMapper()
                        .fromJson(redisTemplate.opsForValue().get(key), PikaArticle.class))) {
                    article = this.sqlSession.selectOne(this.sqlId("findById"), id);
                    //随机过期时间 防止 缓存雪崩
                    int randomMicroseconds = random.nextInt(1000 * 60 * 5) + 1;
                    redisTemplate.opsForValue().set(key, JsonMapper.nonDefaultMapper().toJson(article)
                            , PikaArticleConstant.ARTICLE_TIME_OUT + randomMicroseconds, TimeUnit.MICROSECONDS);
                }
            } finally {
                rLock.unlock();
            }
        }
        return article;
    }
}
