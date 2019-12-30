package com.huiaong.pikachu.trade.impl;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@MapperScan("com.huiaong.pikachu.trade.impl.sms.dao")
public class PikachuTradeConfiguration {

}
