package com.huiaong.pikachu.receiver.trade;

import com.alibaba.dubbo.config.annotation.Reference;
import com.google.common.base.Throwables;
import com.huiaong.pikachu.common.base.producer.MessageDto;
import com.huiaong.pikachu.common.exception.BusinessAssistantException;
import com.huiaong.pikachu.common.response.Response;
import com.huiaong.pikachu.common.util.JsonMapper;
import com.huiaong.pikachu.log.order.enums.PikaPurchaseOrderOperationLogType;
import com.huiaong.pikachu.log.order.model.PikaPurchaseOrderOperationLog;
import com.huiaong.pikachu.log.order.service.PikaPurchaseOrderOperationLogWriteService;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Slf4j
@Component
public class PikaPurchaseOrderReceiver {

    @Reference
    private PikaPurchaseOrderOperationLogWriteService purchaseOrderOperationLogWriteService;

    @RabbitListener(queues = "trade-purchase-create-queue")
    public void purchaseOrderOperationLogCreateProcess(
            @Payload String event,
            @Headers Map<String, Object> properties,
            Channel channel
    ) throws IOException {
        log.info("receiver message:{}", event);

        Long deliveryTag = (Long) properties.get(AmqpHeaders.DELIVERY_TAG);

        try {

            MessageDto messageDto = JsonMapper.nonDefaultMapper().fromJson(event, MessageDto.class);

            PikaPurchaseOrderOperationLog purchaseOrderOperationLog = new PikaPurchaseOrderOperationLog();

            purchaseOrderOperationLog.setCreateId(1L);
            purchaseOrderOperationLog.setPurchaseOrderId(messageDto.getReferenceId());
            purchaseOrderOperationLog.setOperationType(PikaPurchaseOrderOperationLogType.CREATED.value());

            Response<Boolean> booleanResponse = purchaseOrderOperationLogWriteService.create(purchaseOrderOperationLog);
            if (!booleanResponse.isSuccess()) {
                log.error("create purchase order operation log:{} failed, cause by:{}", purchaseOrderOperationLog, booleanResponse.getError());
                throw new BusinessAssistantException("failed.create.purchase.order.operation.log");
            }
            channel.basicAck(deliveryTag, false);

        } catch (Exception e) {
            log.error("consume purchase order create event:{} fail, cause by:{}", event, Throwables.getStackTraceAsString(e));
            channel.basicNack(deliveryTag, false, true);
        }

    }

}
