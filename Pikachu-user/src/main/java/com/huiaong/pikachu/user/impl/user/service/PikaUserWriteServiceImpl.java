package com.huiaong.pikachu.user.impl.user.service;

import com.google.common.base.Throwables;
import com.huiaong.pikachu.common.response.Response;
import com.huiaong.pikachu.common.util.EncryptUtil;
import com.huiaong.pikachu.user.impl.user.dao.PikaUserDao;
import com.huiaong.pikachu.user.user.enums.PikaLoginType;
import com.huiaong.pikachu.user.user.enums.PikaUserStatus;
import com.huiaong.pikachu.user.user.enums.PikaUserType;
import com.huiaong.pikachu.user.user.model.PikaUser;
import com.huiaong.pikachu.user.user.service.PikaUserWriteService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
@com.alibaba.dubbo.config.annotation.Service(cluster = "failfast", timeout = 3000)
public class PikaUserWriteServiceImpl implements PikaUserWriteService {
    private final PikaUserDao pikaUserDao;

    @Override
    public Response<PikaUser> registry(String loginName, String password, Integer loginType, PikaUserType userType) {
        PikaUser user = new PikaUser();
        user.setPassword(EncryptUtil.encrypt(password));
        user.setType(userType.value());
        user.setStatus(PikaUserStatus.NORMAL.value());
        user.setCreateId(0L);

        Boolean createSuccess;
        switch (PikaLoginType.from(loginType)) {
            case NAME:
                user.setName(loginName);
                createSuccess = pikaUserDao.createByName(user);
                break;
            case EMAIL:
                user.setEmail(loginName);
                createSuccess = pikaUserDao.createByEmail(user);
                break;
            case MOBILE:
                user.setMobile(loginName);
                createSuccess = pikaUserDao.createByMobile(user);
                break;
            default:
                return Response.fail("login.type.not.found");
        }

        if (!createSuccess){
            return Response.fail("user.create.fail");
        }
        return Response.ok(user);
    }

    @Override
    public Response<Boolean> logout(String token) {
        try {
            return Response.ok(pikaUserDao.deleteToken(token));
        } catch (Exception e) {
            log.error("logout user by token:{} fail, cause={}", token, Throwables.getStackTraceAsString(e));
            return Response.fail("user.logout.fail");
        }
    }
}
