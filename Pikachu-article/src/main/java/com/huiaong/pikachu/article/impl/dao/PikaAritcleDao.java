package com.huiaong.pikachu.article.impl.dao;

import com.huiaong.pikachu.article.model.PikaArticle;
import com.huiaong.pikachu.common.mysql.dao.MyBatisDao;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PikaAritcleDao extends MyBatisDao<PikaArticle> {
    public List<Long> listIds() {
        return this.sqlSession.selectList(this.sqlId("listIds"));
    }
}
