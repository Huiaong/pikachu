package com.huiaong.pikachu.user.impl.auth.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.huiaong.pikachu.user.auth.service.PikaRoleWriteService;
import com.huiaong.pikachu.user.impl.auth.dao.PikaRoleDao;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@Service(cluster = "failfast", timeout = 3000)
public class PikaRoleWriteServiceImpl implements PikaRoleWriteService {

    private final PikaRoleDao pikaRoleDao;
}
