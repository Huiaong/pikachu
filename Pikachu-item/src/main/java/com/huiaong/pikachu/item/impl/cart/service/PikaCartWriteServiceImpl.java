package com.huiaong.pikachu.item.impl.cart.service;

import com.google.common.base.Throwables;
import com.huiaong.pikachu.common.response.Response;
import com.huiaong.pikachu.item.cart.service.PikaCartWriteService;
import com.huiaong.pikachu.item.impl.cart.dao.PikaCartDao;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
@com.alibaba.dubbo.config.annotation.Service(cluster = "failfast")
public class PikaCartWriteServiceImpl implements PikaCartWriteService {
    private final PikaCartDao pikaCartDao;

    @Override
    public Response<Boolean> addToCart(Long userId, Long goodsId) {
        try {
            return Response.ok(pikaCartDao.addToCart(userId, goodsId));
        } catch (Exception e) {
            log.error("add to cart failed, user id={}, goods id = {}, cause={}", userId, goodsId, Throwables.getStackTraceAsString(e));
            return Response.fail("failed.add.to.cart");
        }
    }

    @Override
    public Response<Boolean> incr(Long userId, Long goodsId) {
        try {
            return Response.ok(pikaCartDao.incr(userId, goodsId));
        } catch (Exception e) {
            log.error("incr goods to cart failed, user id={}, goods id = {}, cause={}", userId, goodsId, Throwables.getStackTraceAsString(e));
            return Response.fail("failed.incr.goods.to.cart");
        }
    }

    @Override
    public Response<Boolean> setQuantity(Long userId, Long goodsId, Integer quantity) {
        try {
            return Response.ok(pikaCartDao.setQuantity(userId, goodsId, quantity));
        } catch (Exception e) {
            log.error("set goods quantity to cart failed, user id={}, goods id = {}, quantity = {}, cause={}", userId, goodsId, quantity, Throwables.getStackTraceAsString(e));
            return Response.fail("failed.set.goods.quantity.to.cart");
        }
    }
}
