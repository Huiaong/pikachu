package com.huiaong.pikachu.user.impl.address.service;

import com.google.common.base.Throwables;
import com.huiaong.pikachu.common.response.Response;
import com.huiaong.pikachu.user.address.model.PikaAddress;
import com.huiaong.pikachu.user.address.service.PikaAddressReadService;
import com.huiaong.pikachu.user.impl.address.dao.PikaAddressDao;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
@com.alibaba.dubbo.config.annotation.Service
public class PikaAddressReadServiceImpl implements PikaAddressReadService {

    private final PikaAddressDao pikaAddressDao;

    public Response<PikaAddress> findById(Long id) {
        try {
            return Response.ok(pikaAddressDao.findById(id));
        } catch (Exception e) {
            log.error("find address by id:{} fail, cause={}", id, Throwables.getStackTraceAsString(e));
            return Response.fail("address.find.fail");
        }
    }
}
