package com.huiaong.pikachu.admin.controller.user;

import com.alibaba.dubbo.config.annotation.Reference;
import com.google.common.base.CharMatcher;
import com.google.common.base.Strings;
import com.huiaong.pikachu.admin.dto.user.PikaRegisterQO;
import com.huiaong.pikachu.admin.vo.user.PikaLoginVO;
import com.huiaong.pikachu.admin.vo.user.PikaUserInfoVO;
import com.huiaong.pikachu.common.response.Response;
import com.huiaong.pikachu.user.user.dto.PikaLoginUser;
import com.huiaong.pikachu.user.user.enums.PikaLoginType;
import com.huiaong.pikachu.user.user.enums.PikaUserType;
import com.huiaong.pikachu.user.user.model.PikaUser;
import com.huiaong.pikachu.user.user.service.PikaUserReadService;
import com.huiaong.pikachu.user.user.service.PikaUserWriteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
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

    @ApiOperation("登陆")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Response<PikaLoginVO> login(@RequestParam String loginName, @RequestParam String password) {
        PikaLoginType type;
        if (CharMatcher.inRange('0', '9').matchesAllOf(loginName)) {
            type = PikaLoginType.MOBILE;
        } else if (CharMatcher.is('@').matchesAnyOf(password)) {
            type = PikaLoginType.EMAIL;
        } else {
            type = PikaLoginType.NAME;
        }
        Response<PikaLoginUser> userResp = pikaUserReadService.login(loginName, password, type);
        if (!userResp.isSuccess()) {
            log.error("user login fail, cause by:{}", userResp.getError());
            return Response.fail(userResp.getError());
        }
        PikaLoginUser loginDTO = userResp.getResult();
        PikaLoginVO loginVO = new PikaLoginVO();
        BeanUtils.copyProperties(loginDTO, loginVO);
        return Response.ok(loginVO);
    }

    @ApiOperation("注册")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Response<PikaUser> registry(@RequestBody @Validated PikaRegisterQO pikaRegisterQO) {
        Response<PikaUser> pikaUserResp = pikaUserWriteService.registry(pikaRegisterQO.getLoginName(), pikaRegisterQO.getPassword(), pikaRegisterQO.getLoginType(), PikaUserType.SALESMAN);
        if (!pikaUserResp.isSuccess()) {
            log.error("registry user by registerQO:{} fail, cause by:{}", pikaRegisterQO, pikaUserResp.getError());
        }
        return pikaUserResp;
    }

    @ApiOperation("登陆信息")
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public Response<PikaUserInfoVO> checkUser(@RequestParam(required = false) String token) {
        if (Strings.isNullOrEmpty(token)) {
            return Response.fail("token.not.allow.null");
        }
        Response<PikaLoginUser> pikaLoginDTOResp = pikaUserReadService.findByToken(token);
        if (!pikaLoginDTOResp.isSuccess()) {
            log.error("find user by token:{} fail, cause by:{}", token, pikaLoginDTOResp.getError());
        }
        PikaLoginUser loginDTO = pikaLoginDTOResp.getResult();
        if (Objects.isNull(loginDTO)) {
            return Response.fail("user.token.expired");
        }

        Response<PikaUser> pikaUserResp = pikaUserReadService.findById(loginDTO.getId());
        if (!pikaUserResp.isSuccess()) {
            log.error("find user by id:{} fail, cause by:{}", token, pikaLoginDTOResp.getError());
        }
        PikaUser pikaUser = pikaUserResp.getResult();
        if (Objects.isNull(pikaUser)) {
            return Response.fail("user.not.exist");
        }
        PikaUserInfoVO pikaUserInfoVO = new PikaUserInfoVO();
        BeanUtils.copyProperties(pikaUser, pikaUserInfoVO);

        return Response.ok(pikaUserInfoVO);
    }

    @ApiOperation("注销")
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public Response<Boolean> logout(@RequestParam @NotBlank(message = "token.must.not.null") String token) {
        Response<Boolean> logoutResp = pikaUserWriteService.logout(token);
        if (!logoutResp.isSuccess()) {
            log.error("logout user by token:{} fail, cause by:{}", token, logoutResp.getError());
            return Response.fail("failed.to.logout");
        }
        return logoutResp;
    }

}
