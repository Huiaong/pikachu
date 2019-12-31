package com.huiaong.pikachu.trade.impl.order.manage;

import com.huiaong.pikachu.common.exception.DataPersistenceException;
import com.huiaong.pikachu.trade.impl.order.dao.PikaPurchaseOrderDao;
import com.huiaong.pikachu.trade.impl.order.dao.PikaPurchaseSkuOrderDao;
import com.huiaong.pikachu.trade.order.dto.PikaPurchaseOrderDto;
import com.huiaong.pikachu.trade.order.model.PikaPurchaseOrder;
import com.huiaong.pikachu.trade.order.model.PikaPurchaseSkuOrder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
public class PikaPurchaseOrderManager {

    private final PikaPurchaseOrderDao pikaPurchaseOrderDao;
    private final PikaPurchaseSkuOrderDao pikaPurchaseSkuOrderDao;

    public PikaPurchaseOrderManager(PikaPurchaseOrderDao pikaPurchaseOrderDao,
                                    PikaPurchaseSkuOrderDao pikaPurchaseSkuOrderDao) {
        this.pikaPurchaseOrderDao = pikaPurchaseOrderDao;
        this.pikaPurchaseSkuOrderDao = pikaPurchaseSkuOrderDao;
    }


    @Transactional(rollbackFor = Exception.class)
    public Boolean create(PikaPurchaseOrderDto pikaPurchaseOrderDto) throws DataPersistenceException {
        PikaPurchaseOrder purchaseOrder = pikaPurchaseOrderDto.getPurchaseOrder();
        PikaPurchaseSkuOrder purchaseSkuOrder = pikaPurchaseOrderDto.getPurchaseSkuOrder();

        Boolean aBoolean = pikaPurchaseOrderDao.create(purchaseOrder);
        if (!aBoolean) {
            log.error("failed create purchase order");
            throw new DataPersistenceException("created.purchase.order.fail");
        }

        Boolean bBoolean = pikaPurchaseSkuOrderDao.create(purchaseSkuOrder);
        if (!bBoolean){
            log.error("failed create purchase sku order");
            throw new DataPersistenceException("created.purchase.sku.order.fail");
        }
        return Boolean.TRUE;
    }
}
