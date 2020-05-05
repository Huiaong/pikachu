package com.huiaong.pikachu.user.userrole.service;

import com.huiaong.pikachu.common.response.Response;

import java.util.List;

public interface PikaUserRoleReadService {
    Response<List<String>> findRolesByUserId(Long userId);
}
