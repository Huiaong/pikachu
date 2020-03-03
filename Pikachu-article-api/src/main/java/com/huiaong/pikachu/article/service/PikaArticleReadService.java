package com.huiaong.pikachu.article.service;

import com.huiaong.pikachu.article.criteria.PikaArticleCriteria;
import com.huiaong.pikachu.article.model.PikaArticle;
import com.huiaong.pikachu.common.pager.Paging;
import com.huiaong.pikachu.common.response.Response;

public interface PikaArticleReadService {

    Response<Paging<PikaArticle>> paging(PikaArticleCriteria criteria);

    Response<PikaArticle> findById(Long id);
}
