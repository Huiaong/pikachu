package com.huiaong.pikachu.trade.order.model;

import com.huiaong.pikachu.common.base.model.PikaBaseBean;
import com.huiaong.pikachu.common.util.DateUtils;
import com.huiaong.pikachu.trade.order.enums.PikaTradeMQResponseStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PikaTradeMQResponse extends PikaBaseBean {

    private String messageId;

    //内容
    private String content;

    //交换机名
    private String exchange;

    //路由键名
    private String routingKey;

    /**
     * 消息发送状态
     * @see com.huiaong.pikachu.trade.order.enums.PikaTradeMQResponseStatus
     */
    private Integer status;

    //重试次数
    private Integer retryCount;

    //下次重试时间
    private Date nextRetry;

    public PikaTradeMQResponse(String messageId, String content, String exchange, String routingKey, Long createId) {
        super.setCreateId(createId);
        this.messageId = messageId;
        this.content = content;
        this.exchange = exchange;
        this.routingKey = routingKey;

        this.retryCount = 0;
        this.status = PikaTradeMQResponseStatus.WAIT_SEND.value();
        this.nextRetry = DateUtils.threeMinutesLater();
    }
}
