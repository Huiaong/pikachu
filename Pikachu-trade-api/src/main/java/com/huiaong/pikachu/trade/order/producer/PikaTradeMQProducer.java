package com.huiaong.pikachu.trade.order.producer;

import com.huiaong.pikachu.common.response.Response;
import com.huiaong.pikachu.trade.order.model.PikaTradeMQResponse;

public interface PikaTradeMQProducer {
    Response<Boolean> send(PikaTradeMQResponse pikaTradeMQResponse);
}
