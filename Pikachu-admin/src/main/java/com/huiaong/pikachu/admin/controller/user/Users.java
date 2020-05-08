package com.huiaong.pikachu.admin.controller.user;

import com.alibaba.dubbo.config.annotation.Reference;
import com.google.common.base.CharMatcher;
import com.google.common.base.Strings;
import com.huiaong.pikachu.admin.QO.user.PikaLoginQO;
import com.huiaong.pikachu.admin.QO.user.PikaRegisterQO;
import com.huiaong.pikachu.admin.VO.user.PikaLoginVO;
import com.huiaong.pikachu.common.response.Response;
import com.huiaong.pikachu.user.user.dto.PikaLoginDTO;
import com.huiaong.pikachu.user.user.enums.PikaLoginType;
import com.huiaong.pikachu.user.user.enums.PikaUserType;
import com.huiaong.pikachu.user.user.model.PikaUser;
import com.huiaong.pikachu.user.user.service.PikaUserReadService;
import com.huiaong.pikachu.user.user.service.PikaUserWriteService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Slf4j
@Api("用户")
@RestController
@RequestMapping(value = "/api/admin/user")
public class Users {

    @Reference
    private PikaUserReadService pikaUserReadService;
    @Reference
    private PikaUserWriteService pikaUserWriteService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Response<PikaLoginVO> login(@RequestBody @Validated PikaLoginQO pikaLoginQO) {
        PikaLoginType type;
        if (CharMatcher.inRange('0', '9').matchesAllOf(pikaLoginQO.getLoginName())) {
            type = PikaLoginType.MOBILE;
        } else if (CharMatcher.is('@').matchesAnyOf(pikaLoginQO.getLoginName())) {
            type = PikaLoginType.EMAIL;
        } else {
            type = PikaLoginType.NAME;
        }
        Response<PikaLoginDTO> userResp = pikaUserReadService.login(pikaLoginQO.getLoginName(), pikaLoginQO.getPassword(), type);
        if (!userResp.isSuccess()) {
            log.error("user login fail, cause by:{}", userResp.getError());
            return Response.fail(userResp.getError());
        }
        PikaLoginDTO loginDTO = userResp.getResult();
        PikaLoginVO loginVO = new PikaLoginVO();
        BeanUtils.copyProperties(loginDTO, loginVO);
        return Response.ok(loginVO);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Response<PikaUser> registry(@RequestBody @Validated PikaRegisterQO pikaRegisterQO) {
        Response<PikaUser> pikaUserResp = pikaUserWriteService.registry(pikaRegisterQO.getLoginName(), pikaRegisterQO.getPassword(), pikaRegisterQO.getLoginType(), PikaUserType.SALESMAN);
        if (!pikaUserResp.isSuccess()) {
            log.error("registry user by registerQO:{} fail, cause by:{}", pikaRegisterQO, pikaUserResp.getError());
        }
        return pikaUserResp;
    }

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public Response<PikaLoginVO> checkUser(@RequestParam(required = false) String token) {
        if (Strings.isNullOrEmpty(token)) {
            return Response.fail("token.not.allow.null");
        }
        Response<PikaLoginDTO> pikaLoginDTOResp = pikaUserReadService.findByToken(token);
        if (!pikaLoginDTOResp.isSuccess()) {
            log.error("find user by token:{} fail, cause by:{}", token, pikaLoginDTOResp.getError());
        }
        PikaLoginDTO loginDTO = pikaLoginDTOResp.getResult();
        if (Objects.isNull(loginDTO)) {
            return Response.fail("user.token.expired");
        }
        PikaLoginVO loginVO = new PikaLoginVO();
        BeanUtils.copyProperties(loginDTO, loginVO);
        return Response.ok(loginVO);
    }

}
