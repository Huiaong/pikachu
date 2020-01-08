package com.huiaong.pikachu.trade.order.service;

import com.huiaong.pikachu.common.response.Response;
import com.huiaong.pikachu.trade.order.model.PikaTradeMQResponse;

import java.util.List;

public interface PikaTradeMQResponseReadService {
    Response<PikaTradeMQResponse> findByMessageId(String messageId);

    Response<List<PikaTradeMQResponse>> findArticleOneHundredFailToSendMessage();
}
