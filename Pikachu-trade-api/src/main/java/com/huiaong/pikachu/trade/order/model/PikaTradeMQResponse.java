package com.huiaong.pikachu.trade.order.model;

import com.huiaong.pikachu.common.base.producer.Event;
import com.huiaong.pikachu.common.util.DateUtils;
import com.huiaong.pikachu.trade.order.enums.PikaTradeMQResponseStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PikaTradeMQResponse extends Event {

    /**
     * 消息发送状态
     *
     * @see com.huiaong.pikachu.trade.order.enums.PikaTradeMQResponseStatus
     */
    private Integer status;

    //重试次数
    private Integer retryCount;

    //下次重试时间
    private Date nextRetry;

    public PikaTradeMQResponse(String messageId, String exchange, String routingKey, String content, Long createId) {
        super.setMessageId(messageId);
        super.setContent(content);
        super.setExchange(exchange);
        super.setRoutingKey(routingKey);
        super.setCreateId(createId);

        this.retryCount = 0;
        this.status = PikaTradeMQResponseStatus.WAIT_SEND.value();
        this.nextRetry = DateUtils.threeMinutesLater();
    }
}
