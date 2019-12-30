package com.huiaong.pikachu.trade.impl.sms.service;

import com.huiaong.pikachu.trade.impl.sms.dao.NormalMessageDao;
import com.huiaong.pikachu.trade.sms.model.NormalMessage;
import com.huiaong.pikachu.trade.sms.service.NormalMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@com.alibaba.dubbo.config.annotation.Service
public class NormalMessageServiceImpl implements NormalMessageService {

    private final NormalMessageDao normalMessageDao;

    @Autowired
    public NormalMessageServiceImpl(NormalMessageDao normalMessageDao) {
        this.normalMessageDao = normalMessageDao;
    }

    @Override
    public Boolean create(NormalMessage normalMessage) {
        return normalMessageDao.create(normalMessage) >= 1;
    }
}
