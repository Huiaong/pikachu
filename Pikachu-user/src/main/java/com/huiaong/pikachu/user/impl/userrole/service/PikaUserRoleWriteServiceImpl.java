package com.huiaong.pikachu.user.impl.userrole.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.huiaong.pikachu.user.impl.userrole.dao.PikaUserRoleDao;
import com.huiaong.pikachu.user.userrole.service.PikaUserRoleWriteService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@Service(cluster = "failfast", timeout = 3000)
public class PikaUserRoleWriteServiceImpl implements PikaUserRoleWriteService {
    private final PikaUserRoleDao pikaUserRoleDao;

}
