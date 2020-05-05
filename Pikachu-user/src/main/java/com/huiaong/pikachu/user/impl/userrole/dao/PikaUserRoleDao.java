package com.huiaong.pikachu.user.impl.userrole.dao;

import com.huiaong.pikachu.common.mysql.dao.MyBatisDao;
import com.huiaong.pikachu.user.userrole.model.PikaUserRole;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PikaUserRoleDao extends MyBatisDao<PikaUserRole> {
    public List<PikaUserRole> findByUserId(Long userId) {
        return sqlSession.selectList(sqlId("findByUserId"), userId);
    }
}
