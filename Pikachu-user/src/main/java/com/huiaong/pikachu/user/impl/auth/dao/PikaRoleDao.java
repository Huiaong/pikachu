package com.huiaong.pikachu.user.impl.auth.dao;

import com.huiaong.pikachu.common.mysql.dao.MyBatisDao;
import com.huiaong.pikachu.user.auth.model.PikaRole;
import com.huiaong.pikachu.user.user.model.PikaUser;
import org.springframework.stereotype.Repository;

@Repository
public class PikaRoleDao extends MyBatisDao<PikaRole> {
}
