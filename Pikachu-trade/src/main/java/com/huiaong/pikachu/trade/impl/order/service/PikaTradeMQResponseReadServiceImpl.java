package com.huiaong.pikachu.trade.impl.order.service;

import com.google.common.base.Throwables;
import com.huiaong.pikachu.common.response.Response;
import com.huiaong.pikachu.trade.impl.order.dao.PikaTradeMQResponseDao;
import com.huiaong.pikachu.trade.order.model.PikaTradeMQResponse;
import com.huiaong.pikachu.trade.order.service.PikaTradeMQResponseReadService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
@com.alibaba.dubbo.config.annotation.Service
public class PikaTradeMQResponseReadServiceImpl implements PikaTradeMQResponseReadService {

    private final PikaTradeMQResponseDao tradeMQResponseDao;

    @Override
    public Response<PikaTradeMQResponse> findByMessageId(String messageId) {
        try {
            return Response.ok(tradeMQResponseDao.findByMessageId(messageId));
        } catch (Exception e) {
            log.error("find trade mq response by message id:{} fail, cause={}", messageId, Throwables.getStackTraceAsString(e));
            return Response.fail("trade.mq.response.find.fail");
        }
    }

    @Override
    public Response<List<PikaTradeMQResponse>> findArticleOneHundredFailToSendMessage() {
        try {
            return Response.ok(tradeMQResponseDao.findArticleOneHundredFailToSendMessage());
        } catch (Exception e) {
            log.error("find article one hundred fail to send message trade mq response fail, cause={}", Throwables.getStackTraceAsString(e));
            return Response.fail("article.one.hundred.fail.to.send.message.trade.mq.response.find.fail");
        }
    }
}
