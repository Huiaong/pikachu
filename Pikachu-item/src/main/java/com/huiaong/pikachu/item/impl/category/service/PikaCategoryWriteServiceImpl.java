package com.huiaong.pikachu.item.impl.category.service;


import com.alibaba.dubbo.config.annotation.Service;
import com.huiaong.pikachu.item.category.service.PikaCategoryWriteService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@Service(cluster = "failfast", timeout = 3000)
public class PikaCategoryWriteServiceImpl implements PikaCategoryWriteService {
}
