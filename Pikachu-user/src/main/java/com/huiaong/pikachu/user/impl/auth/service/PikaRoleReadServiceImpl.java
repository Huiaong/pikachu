package com.huiaong.pikachu.user.impl.auth.service;

import com.huiaong.pikachu.user.auth.service.PikaRoleReadService;
import com.huiaong.pikachu.user.impl.auth.dao.PikaRoleDao;
import com.huiaong.pikachu.user.impl.user.dao.PikaUserDao;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
@com.alibaba.dubbo.config.annotation.Service(timeout = 3000)
public class PikaRoleReadServiceImpl implements PikaRoleReadService {

    private final PikaRoleDao pikaRoleDao;
}
