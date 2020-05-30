package com.huiaong.pikachu.admin.controller.category;

import com.alibaba.dubbo.config.annotation.Reference;
import com.huiaong.pikachu.common.pager.Paging;
import com.huiaong.pikachu.common.response.Response;
import com.huiaong.pikachu.item.category.criteria.PikaCategoryCriteria;
import com.huiaong.pikachu.item.category.model.PikaCategory;
import com.huiaong.pikachu.item.category.service.PikaCategoryReadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Api("商品类目")
@RestController
@RequestMapping("/api/admin/category")
public class Categories {
    @Reference
    private PikaCategoryReadService pikaCategoryReadService;

    @ApiOperation("商品类目分页")
    @RequestMapping(value = "/paging", method = RequestMethod.GET)
    public Response<Paging<PikaCategory>> paging(PikaCategoryCriteria criteria) {
        Response<Paging<PikaCategory>> pagingResp = pikaCategoryReadService.paging(criteria);
        if (!pagingResp.isSuccess()) {
            log.error("paging by category criteria:{} failed, cause by:{}", criteria, pagingResp.getError());
        }
        return pagingResp;
    }

}
