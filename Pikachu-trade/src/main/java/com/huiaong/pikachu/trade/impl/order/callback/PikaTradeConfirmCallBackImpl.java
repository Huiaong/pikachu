package com.huiaong.pikachu.trade.impl.order.callback;

import com.huiaong.pikachu.common.response.Response;
import com.huiaong.pikachu.trade.impl.order.dao.PikaTradeMQResponseDao;
import com.huiaong.pikachu.trade.order.enums.PikaTradeMQResponseStatus;
import com.huiaong.pikachu.trade.order.model.PikaTradeMQResponse;
import com.huiaong.pikachu.trade.order.service.PikaTradeMQResponseReadService;
import com.huiaong.pikachu.trade.order.service.PikaTradeMQResponseWriteService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Slf4j
@Component
public class PikaTradeConfirmCallBackImpl implements RabbitTemplate.ConfirmCallback {

    private final Integer MAX_RETRY_COUNT = 3;

    @Autowired
    private PikaTradeMQResponseDao pikaTradeMQResponseDao;

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {

        String messageId = correlationData.getId();

        PikaTradeMQResponse tradeMQResponse = pikaTradeMQResponseDao.findByMessageId(messageId);


        if (ack) {
            tradeMQResponse.setStatus(PikaTradeMQResponseStatus.HAS_SEND.value());
            pikaTradeMQResponseDao.update(tradeMQResponse);
            log.info("message(id:{}) send success", messageId);
        } else if (Objects.equals(tradeMQResponse.getRetryCount(), MAX_RETRY_COUNT)){
            tradeMQResponse.setStatus(PikaTradeMQResponseStatus.FAIL_SEND.value());
            pikaTradeMQResponseDao.update(tradeMQResponse);
            log.info("message(id:{}) send fail, retry count get to max", messageId);
        }else {
            log.error("message(id:{}) send error, cause by:{}", messageId, cause);
        }

    }
}
