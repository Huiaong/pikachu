package com.huiaong.pikachu.crontab.mq;

import com.alibaba.dubbo.config.annotation.Reference;
import com.google.common.base.Throwables;
import com.huiaong.pikachu.common.response.Response;
import com.huiaong.pikachu.trade.order.model.PikaTradeMQResponse;
import com.huiaong.pikachu.trade.order.producer.PikaTradeMQProducer;
import com.huiaong.pikachu.trade.order.service.PikaTradeMQResponseReadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Component
@Slf4j
public class PikaResendTradeResponseJob {


    @Reference
    private PikaTradeMQResponseReadService tradeMQResponseService;
    @Reference
    private PikaTradeMQProducer tradeMQProducer;


    @Scheduled(fixedRate = 1000 * 5, initialDelay = 1000 * 5)
    public void autoRetry() {
        Response<List<PikaTradeMQResponse>> tradeMQResponseListResp = tradeMQResponseService.findArticleOneHundredFailToSendMessage();
        if (!tradeMQResponseListResp.isSuccess()){
            log.error("find article one hundred fail to send message trade mq response fail, cause={}", tradeMQResponseListResp.getError());
        }
        List<PikaTradeMQResponse> tradeMQResponseList = tradeMQResponseListResp.getResult();
        if (CollectionUtils.isEmpty(tradeMQResponseList)) return;
        tradeMQResponseList.forEach(tradeMQProducer::send);
    }
}
