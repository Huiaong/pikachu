package com.huiaong.pikachu.trade.impl.order.callback;

import com.huiaong.pikachu.common.util.DateUtils;
import com.huiaong.pikachu.trade.impl.order.dao.PikaTradeMQResponseDao;
import com.huiaong.pikachu.trade.order.enums.PikaTradeMQResponseStatus;
import com.huiaong.pikachu.trade.order.model.PikaTradeMQResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.security.InvalidParameterException;
import java.util.Date;

@Slf4j
@Component
@AllArgsConstructor
public class PikaTradeConfirmCallBackImpl implements RabbitTemplate.ConfirmCallback {

    private final Integer MAX_RETRY_COUNT = 3;

    private final PikaTradeMQResponseDao pikaTradeMQResponseDao;

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {

        String messageId = correlationData.getId();

        PikaTradeMQResponse tradeMQResponse = pikaTradeMQResponseDao.findByMessageId(messageId);


        if (ack) {
            tradeMQResponse.setStatus(PikaTradeMQResponseStatus.HAS_SEND.value());
            log.info("message(id:{}) send success", messageId);
        } else if (tradeMQResponse.getRetryCount() > MAX_RETRY_COUNT) {
            tradeMQResponse.setStatus(PikaTradeMQResponseStatus.FAIL_SEND.value());
            log.info("message(id:{}) send fail, retry count get to max", messageId);
        } else {
            Integer currentRetryCount = tradeMQResponse.getRetryCount();
            tradeMQResponse.setRetryCount(++currentRetryCount);
            tradeMQResponse.setNextRetry(this.generateNextRetry(currentRetryCount));

            log.error("message(id:{}) send error, cause by:{}", messageId, cause);
        }

        pikaTradeMQResponseDao.update(tradeMQResponse);

    }

    private Date generateNextRetry(Integer currentRetryCount) {
        switch (currentRetryCount) {
            case 1:
                return DateUtils.threeMinutesLater();
            case 2:
                return DateUtils.fiveMinutesLater();
            case 3:
                return DateUtils.fifteenMinutesLater();
            default:
                throw new InvalidParameterException("param not allow [" + currentRetryCount + "]");
        }
    }
}
