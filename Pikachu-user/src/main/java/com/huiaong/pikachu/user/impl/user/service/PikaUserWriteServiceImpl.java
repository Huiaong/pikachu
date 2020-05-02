package com.huiaong.pikachu.user.impl.user.service;

import com.huiaong.pikachu.user.impl.user.dao.PikaUserDao;
import com.huiaong.pikachu.user.user.service.PikaUserWriteService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
@com.alibaba.dubbo.config.annotation.Service(cluster = "failfast")
public class PikaUserWriteServiceImpl implements PikaUserWriteService {
    private final PikaUserDao pikaUserDao;

}
