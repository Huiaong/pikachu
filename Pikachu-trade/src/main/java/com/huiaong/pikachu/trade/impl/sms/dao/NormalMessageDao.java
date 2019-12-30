package com.huiaong.pikachu.trade.impl.sms.dao;

import com.huiaong.pikachu.trade.sms.model.NormalMessage;
import org.springframework.stereotype.Repository;

@Repository
public interface NormalMessageDao {
    int create(NormalMessage normalMessage);
}
