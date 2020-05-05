package com.huiaong.pikachu.user.impl.manager;

import com.huiaong.pikachu.user.auth.model.PikaRole;
import com.huiaong.pikachu.user.impl.auth.dao.PikaRoleDao;
import com.huiaong.pikachu.user.impl.userrole.dao.PikaUserRoleDao;
import com.huiaong.pikachu.user.userrole.model.PikaUserRole;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
@AllArgsConstructor
public class PikaUserManager {
    private final PikaUserRoleDao pikaUserRoleDao;
    private final PikaRoleDao pikaRoleDao;

    @Transactional
    public List<String> findRolesByUserId(Long userId) {
        List<PikaUserRole> userRoleList = pikaUserRoleDao.findByUserId(userId);
        List<Long> roleIdList = userRoleList.stream().map(PikaUserRole::getRoleId).collect(Collectors.toList());
        List<PikaRole> roleList = pikaRoleDao.findByIds(roleIdList);
        return roleList.stream().map(PikaRole::getValue).collect(Collectors.toList());
    }
}
