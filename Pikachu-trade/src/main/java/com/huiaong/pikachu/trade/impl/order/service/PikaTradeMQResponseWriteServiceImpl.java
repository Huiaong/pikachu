package com.huiaong.pikachu.trade.impl.order.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.huiaong.pikachu.trade.order.service.PikaTradeMQResponseWriteService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@Service(cluster = "failfast", timeout = 3000)
public class PikaTradeMQResponseWriteServiceImpl implements PikaTradeMQResponseWriteService {
}
