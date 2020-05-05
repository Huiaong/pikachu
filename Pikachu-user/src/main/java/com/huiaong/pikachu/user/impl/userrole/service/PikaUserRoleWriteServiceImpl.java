package com.huiaong.pikachu.user.impl.userrole.service;

import com.huiaong.pikachu.user.impl.userrole.dao.PikaUserRoleDao;
import com.huiaong.pikachu.user.user.service.PikaUserWriteService;
import com.huiaong.pikachu.user.userrole.service.PikaUserRoleWriteService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
@com.alibaba.dubbo.config.annotation.Service(cluster = "failfast")
public class PikaUserRoleWriteServiceImpl implements PikaUserRoleWriteService {
    private final PikaUserRoleDao pikaUserRoleDao;

}
