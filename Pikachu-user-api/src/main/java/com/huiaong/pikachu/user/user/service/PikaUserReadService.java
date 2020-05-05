package com.huiaong.pikachu.user.user.service;

import com.huiaong.pikachu.common.response.Response;
import com.huiaong.pikachu.user.user.dto.PikaLoginDTO;
import com.huiaong.pikachu.user.user.enums.PikaLoginType;
import com.huiaong.pikachu.user.user.model.PikaUser;

public interface PikaUserReadService {
    Response<PikaLoginDTO> findByToken(String token);

    Response<PikaLoginDTO> login(String loginName, String password, PikaLoginType loginType);

    Response<PikaUser> findByLoginNameAndLoginType(String loginName, PikaLoginType loginType);
}
