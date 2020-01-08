package com.huiaong.pikachu.trade.impl.order.dao;

import com.huiaong.pikachu.common.mysql.dao.MyBatisDao;
import com.huiaong.pikachu.trade.order.model.PikaTradeMQResponse;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PikaTradeMQResponseDao extends MyBatisDao<PikaTradeMQResponse> {

    public PikaTradeMQResponse findByMessageId(String messageId) {
        return this.sqlSession.selectOne(this.sqlId("findByMessageId"), messageId);
    }

    public List<PikaTradeMQResponse> findArticleOneHundredFailToSendMessage() {
        return this.sqlSession.selectList(this.sqlId("findArticleOneHundredFailToSendMessage"));
    }
}
