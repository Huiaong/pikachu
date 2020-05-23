package com.huiaong.pikachu.trade.impl.order.producer;

import com.alibaba.dubbo.config.annotation.Service;
import com.huiaong.pikachu.common.base.producer.AbstractProducer;
import com.huiaong.pikachu.common.response.Response;
import com.huiaong.pikachu.trade.order.model.PikaTradeMQResponse;
import com.huiaong.pikachu.trade.order.producer.PikaTradeMQProducer;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@Service(timeout = 3000)
public class PikaTradeMQProducerImpl extends AbstractProducer implements PikaTradeMQProducer {

    @Override
    public Response<Boolean> send(PikaTradeMQResponse pikaTradeMQResponse) {
        super.send(pikaTradeMQResponse);

        return Response.ok(Boolean.TRUE);
    }
}
