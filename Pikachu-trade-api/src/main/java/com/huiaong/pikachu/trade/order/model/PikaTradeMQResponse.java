package com.huiaong.pikachu.trade.order.model;

import com.huiaong.pikachu.common.base.model.PikaBaseBean;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
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

}
