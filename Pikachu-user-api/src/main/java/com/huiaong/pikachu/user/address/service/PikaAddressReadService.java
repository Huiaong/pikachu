package com.huiaong.pikachu.user.address.service;

import com.huiaong.pikachu.common.response.Response;
import com.huiaong.pikachu.user.address.model.PikaAddress;

public interface PikaAddressReadService {
    Response<PikaAddress> findById(Long id);
}
