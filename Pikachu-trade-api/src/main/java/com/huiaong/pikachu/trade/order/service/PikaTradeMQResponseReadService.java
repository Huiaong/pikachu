package com.huiaong.pikachu.trade.order.service;

import com.huiaong.pikachu.common.response.Response;
import com.huiaong.pikachu.trade.order.model.PikaTradeMQResponse;

public interface PikaTradeMQResponseReadService {
    Response<PikaTradeMQResponse> findByMessageId(String messageId);
}
