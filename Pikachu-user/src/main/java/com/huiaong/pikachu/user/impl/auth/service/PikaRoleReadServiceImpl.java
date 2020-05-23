package com.huiaong.pikachu.user.impl.auth.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.huiaong.pikachu.user.auth.service.PikaRoleReadService;
import com.huiaong.pikachu.user.impl.auth.dao.PikaRoleDao;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@Service(timeout = 3000)
public class PikaRoleReadServiceImpl implements PikaRoleReadService {

    private final PikaRoleDao pikaRoleDao;
}
