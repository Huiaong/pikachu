package com.huiaong.pikachu.user.user.service;

import com.huiaong.pikachu.common.response.Response;
import com.huiaong.pikachu.user.user.enums.PikaUserType;
import com.huiaong.pikachu.user.user.model.PikaUser;

public interface PikaUserWriteService {
    Response<PikaUser> registry(String loginName, String password, Integer loginType, PikaUserType userType);

    Response<Boolean> logout(String token);

    Response<Boolean> refreshToken(String token);
}
