package com.huiaong.pikachu.web.core.interceptor;

import com.alibaba.dubbo.config.annotation.Reference;
import com.huiaong.pikachu.common.response.Response;
import com.huiaong.pikachu.common.util.UserUtil;
import com.huiaong.pikachu.user.user.dto.PikaLoginUser;
import com.huiaong.pikachu.user.user.service.PikaUserReadService;
import com.huiaong.pikachu.user.user.service.PikaUserWriteService;
import com.huiaong.pikachu.web.core.util.CookieUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class PikachuLoginInterceptor extends HandlerInterceptorAdapter {

    @Reference
    private PikaUserReadService pikaUserReadService;
    @Reference
    private PikaUserWriteService pikaUserWriteService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = CookieUtil.getCookieValue(request, "token.pikachu.com");
        if (StringUtils.isBlank(token)) return false;

        Response<PikaLoginUser> loginResp = pikaUserReadService.findByToken(token);
        if (!loginResp.isSuccess()) {
            log.error("token:{} has been expired", token);
            return false;
        }

        UserUtil.putCurrentUser(loginResp.getResult());

        Response<Boolean> refreshResp = pikaUserWriteService.refreshToken(token);
        if (!refreshResp.isSuccess()) {
            log.error("failed to refresh token:{}", token);
        }

        return true;
    }
}
