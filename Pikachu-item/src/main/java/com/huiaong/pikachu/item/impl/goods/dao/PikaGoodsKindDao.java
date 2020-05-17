package com.huiaong.pikachu.item.impl.goods.dao;

import com.huiaong.pikachu.common.mysql.dao.MyBatisDao;
import com.huiaong.pikachu.item.goods.model.PikaGoodsKind;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PikaGoodsKindDao extends MyBatisDao<PikaGoodsKind> {

    public List<PikaGoodsKind> findByGoodsId(Long goodsId) {
        return sqlSession.selectList(sqlId("findByGoodsId"), goodsId);
    }
}
