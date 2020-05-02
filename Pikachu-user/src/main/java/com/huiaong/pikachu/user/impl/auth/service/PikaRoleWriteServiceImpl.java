package com.huiaong.pikachu.user.impl.auth.service;

import com.huiaong.pikachu.user.auth.service.PikaRoleReadService;
import com.huiaong.pikachu.user.auth.service.PikaRoleWriteService;
import com.huiaong.pikachu.user.impl.auth.dao.PikaRoleDao;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
@com.alibaba.dubbo.config.annotation.Service(cluster = "failfast")
public class PikaRoleWriteServiceImpl implements PikaRoleWriteService {

    private final PikaRoleDao pikaRoleDao;
}
