package com.huiaong.pikachu.user.impl.user.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.google.common.base.Throwables;
import com.huiaong.pikachu.common.response.Response;
import com.huiaong.pikachu.common.util.EncryptUtil;
import com.huiaong.pikachu.user.auth.model.PikaRole;
import com.huiaong.pikachu.user.impl.auth.dao.PikaRoleDao;
import com.huiaong.pikachu.user.impl.user.dao.PikaUserDao;
import com.huiaong.pikachu.user.user.dto.PikaLoginUser;
import com.huiaong.pikachu.user.user.enums.PikaLoginType;
import com.huiaong.pikachu.user.user.enums.PikaUserStatus;
import com.huiaong.pikachu.user.user.model.PikaUser;
import com.huiaong.pikachu.user.user.service.PikaUserReadService;
import com.huiaong.pikachu.user.userrole.model.PikaUserRole;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
@Service(timeout = 3000)
public class PikaUserReadServiceImpl implements PikaUserReadService {

    private final PikaUserDao pikaUserDao;
    private final PikaRoleDao pikaRoleDao;

    @Override
    public Response<PikaLoginUser> findByToken(String token) {
        try {
            return Response.ok(pikaUserDao.findByToken(token));
        } catch (Exception e) {
            log.error("find user by token:{} fail, cause={}", token, Throwables.getStackTraceAsString(e));
            return Response.fail("user.find.fail");
        }
    }

    @Override
    public Response<PikaLoginUser> login(String loginName, String password, PikaLoginType loginType) {
        try {
            Response<PikaUser> pikaUserResp = this.findByLoginNameAndLoginType(loginName, loginType);
            if (!pikaUserResp.isSuccess()) {
                return Response.fail(pikaUserResp.getError());
            }

            PikaUser user = pikaUserResp.getResult();
            switch (PikaUserStatus.from(user.getStatus())) {
                case NORMAL:
                    if (!EncryptUtil.match(password, user.getPassword())) {
                        log.error("user(loginName={}, loginType={})'s password mismatch, login failed", loginName, loginType);
                        return Response.fail("user.password.mismatch");
                    }

                    PikaLoginUser pikaLoginUser = new PikaLoginUser();
                    BeanUtils.copyProperties(user, pikaLoginUser);

                    String token = UUID.randomUUID().toString();
                    pikaLoginUser.setToken(token);

                    List<PikaRole> roles = pikaRoleDao.findByIds(user.getRoleIds().stream().map(PikaUserRole::getRoleId).collect(Collectors.toList()));
                    pikaLoginUser.setRoles(roles.stream().map(PikaRole::getName).collect(Collectors.toList()));

                    pikaUserDao.cacheToken(pikaLoginUser);

                    return Response.ok(pikaLoginUser);
                case LOCKED:
                    return Response.fail("user.status.locked");
                case FREEZE:
                    return Response.fail("user.status.freeze");
                case DELETED:
                    return Response.fail("user.status.deleted");
                default:
                    throw new IllegalStateException("Unexpected value: " + user.getStatus());
            }
        } catch (Exception e) {
            log.error("user login by loginName:{} password:{} loginType:{} fail, cause={}", loginName, password, loginType, Throwables.getStackTraceAsString(e));
            return Response.fail("user.login.fail");
        }
    }

    @Override
    public Response<PikaUser> findByLoginNameAndLoginType(String loginName, PikaLoginType loginType) {
        try {
            PikaUser user;
            switch (loginType) {
                case NAME:
                    user = pikaUserDao.findByName(loginName);
                    break;
                case EMAIL:
                    user = pikaUserDao.findByEmail(loginName);
                    break;
                case MOBILE:
                    user = pikaUserDao.findByMobile(loginName);
                    break;
                default:
                    return Response.fail("user.not.found");
            }
            if (Objects.isNull(user)) {
                log.error("user(loginName={}, loginType={}) not found", loginName, loginType);
                return Response.fail("user.not.found");
            }
            return Response.ok(user);
        } catch (Exception e) {
            log.error("find user by login name:{} and login type:{} fail, cause={}", loginName, loginType, Throwables.getStackTraceAsString(e));
            return Response.fail("user.find.fail");
        }
    }

    @Override
    public Response<PikaUser> findById(Long id) {
        try {
            return Response.ok(pikaUserDao.findById(id));
        } catch (Exception e) {
            log.error("find user by id:{} fail, cause={}", id, Throwables.getStackTraceAsString(e));
            return Response.fail("user.find.fail");
        }
    }
}
