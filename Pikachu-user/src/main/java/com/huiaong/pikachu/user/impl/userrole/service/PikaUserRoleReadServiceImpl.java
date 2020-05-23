package com.huiaong.pikachu.user.impl.userrole.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.google.common.base.Throwables;
import com.huiaong.pikachu.common.response.Response;
import com.huiaong.pikachu.user.impl.manager.PikaUserManager;
import com.huiaong.pikachu.user.impl.userrole.dao.PikaUserRoleDao;
import com.huiaong.pikachu.user.userrole.service.PikaUserRoleReadService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@AllArgsConstructor
@Service(timeout = 3000)
public class PikaUserRoleReadServiceImpl implements PikaUserRoleReadService {
    private final PikaUserRoleDao pikaUserRoleDao;
    private final PikaUserManager pikaUserManager;


    @Override
    public Response<List<String>> findRolesByUserId(Long userId) {
        try {
            return Response.ok(pikaUserManager.findRolesByUserId(userId));
        } catch (Exception e) {
            log.error("find user role by user id:{} fail, cause={}", userId, Throwables.getStackTraceAsString(e));
            return Response.fail("user.role.find.fail");
        }
    }
}
