package com.huiaong.pikachu.admin.article.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.huiaong.pikachu.article.criteria.PikaArticleCriteria;
import com.huiaong.pikachu.article.model.PikaArticle;
import com.huiaong.pikachu.article.service.PikaArticleReadService;
import com.huiaong.pikachu.article.service.PikaArticleWriteService;
import com.huiaong.pikachu.common.pager.Paging;
import com.huiaong.pikachu.common.response.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Api("文章")
@RestController
@RequestMapping(value = "/api/admin/article")
public class Articles {

    @Reference
    private PikaArticleWriteService pikaArticleWriteService;
    @Reference
    private PikaArticleReadService pikaArticleReadService;

    @ApiOperation("文章分页")
    @RequestMapping(value = "/paging", method = RequestMethod.GET)
    public Paging<PikaArticle> paging(PikaArticleCriteria criteria) {
        Response<Paging<PikaArticle>> pagingResponse = pikaArticleReadService.paging(criteria);
        if (!pagingResponse.isSuccess()) {
            log.error("paging by article criteria:{} failed, cause by:{}", criteria, pagingResponse.getError());
        }
        return pagingResponse.getResult();
    }

    @ApiOperation("获取某篇文章")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Response<PikaArticle> findById(@PathVariable Long id) {
        Response<PikaArticle> pikaArticleResponse = pikaArticleReadService.findById(id);
        if (!pikaArticleResponse.isSuccess()) {
            log.error("find article by id:{} failed, cause by:{}", id, pikaArticleResponse.getError());
        }
        return pikaArticleResponse;
    }
}
