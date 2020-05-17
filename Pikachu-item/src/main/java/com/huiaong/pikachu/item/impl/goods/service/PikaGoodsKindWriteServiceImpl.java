package com.huiaong.pikachu.item.impl.goods.service;

import com.huiaong.pikachu.item.goods.service.PikaGoodsKindWriteService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
@com.alibaba.dubbo.config.annotation.Service(cluster = "failfast", timeout = 3000)
public class PikaGoodsKindWriteServiceImpl implements PikaGoodsKindWriteService {
}
