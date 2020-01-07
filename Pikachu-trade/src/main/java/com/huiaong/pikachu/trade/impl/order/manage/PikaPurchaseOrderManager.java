package com.huiaong.pikachu.trade.impl.order.manage;

import com.huiaong.pikachu.common.base.producer.Event;
import com.huiaong.pikachu.common.base.producer.MessageDto;
import com.huiaong.pikachu.common.exception.DataPersistenceException;
import com.huiaong.pikachu.common.util.DateUtils;
import com.huiaong.pikachu.trade.impl.order.dao.PikaPurchaseOrderDao;
import com.huiaong.pikachu.trade.impl.order.dao.PikaPurchaseSkuOrderDao;
import com.huiaong.pikachu.trade.impl.order.producer.PikaPurchaseOrderProducer;
import com.huiaong.pikachu.trade.order.dto.PikaPurchaseOrderDto;
import com.huiaong.pikachu.trade.order.event.PikaPurchaseOrderEvent;
import com.huiaong.pikachu.trade.order.model.PikaPurchaseOrder;
import com.huiaong.pikachu.trade.order.model.PikaPurchaseSkuOrder;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Slf4j
@Component
@AllArgsConstructor
public class PikaPurchaseOrderManager {

    private final PikaPurchaseOrderDao pikaPurchaseOrderDao;
    private final PikaPurchaseSkuOrderDao pikaPurchaseSkuOrderDao;
    private final PikaPurchaseOrderProducer pikaPurchaseOrderProducer;


    @Transactional(rollbackFor = Exception.class)
    public Boolean create(PikaPurchaseOrderDto pikaPurchaseOrderDto) throws DataPersistenceException {
        PikaPurchaseOrder purchaseOrder = pikaPurchaseOrderDto.getPurchaseOrder();
        List<PikaPurchaseSkuOrder> purchaseSkuOrders = pikaPurchaseOrderDto.getPurchaseSkuOrders();

        Boolean aBoolean = pikaPurchaseOrderDao.create(purchaseOrder);
        if (!aBoolean) {
            log.error("failed create purchase order");
            throw new DataPersistenceException("created.purchase.order.fail");
        }

        purchaseSkuOrders.forEach(purchaseSkuOrder -> {
            purchaseSkuOrder.setPurchaseOrderId(purchaseOrder.getId());

            Boolean bBoolean = pikaPurchaseSkuOrderDao.create(purchaseSkuOrder);
            if (!bBoolean) {
                log.error("failed create purchase sku order");
                throw new DataPersistenceException("created.purchase.sku.order.fail");
            }
        });

        String messageId = UUID.randomUUID().toString().replace("-", "") + DateUtils.formatDate(new Date(), "yyyyMMdd");
        MessageDto messageDto = new MessageDto(purchaseOrder.getId());
        Event event = new PikaPurchaseOrderEvent(messageId, "trade-purchase-create-exchange", "", messageDto);
        pikaPurchaseOrderProducer.send(event);

        return Boolean.TRUE;
    }
}
