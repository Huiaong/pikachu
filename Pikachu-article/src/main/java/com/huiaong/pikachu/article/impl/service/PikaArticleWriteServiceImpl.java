package com.huiaong.pikachu.article.impl.service;

import com.huiaong.pikachu.article.constant.PikaArticleConstant;
import com.huiaong.pikachu.article.impl.dao.PikaAritcleDao;
import com.huiaong.pikachu.article.service.PikaArticleWriteService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
@com.alibaba.dubbo.config.annotation.Service(cluster = "failfast")
public class PikaArticleWriteServiceImpl implements PikaArticleWriteService {
    private final PikaAritcleDao pikaAritcleDao;
    private final RedissonClient redissonClient;

    @PostConstruct
    public void fullDump() {
        log.info("[Article BloomFilter] - start full dump");
        RBloomFilter<Object> bloomFilter = redissonClient.getBloomFilter(PikaArticleConstant.ARTICLE_BF);
        List<Long> ids = pikaAritcleDao.listIds();
        bloomFilter.tryInit(PikaArticleConstant.ARTICLE_BF_ESTIMATE_LENGTH, PikaArticleConstant.ARtICLE_BF_EStiMAtE_ERROR);
        ids.forEach(bloomFilter::add);
    }
}
