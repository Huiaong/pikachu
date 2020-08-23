package com.huiaong.pikachu.admin.interceptor;

import com.alibaba.dubbo.config.annotation.Reference;
import com.google.common.base.Throwables;
import com.huiaong.pikachu.admin.annotation.Auth;
import com.huiaong.pikachu.common.base.model.PikaBaseUser;
import com.huiaong.pikachu.common.response.Response;
import com.huiaong.pikachu.common.util.JsonMapper;
import com.huiaong.pikachu.common.util.UserUtil;
import com.huiaong.pikachu.user.user.service.PikaUserReadService;
import com.huiaong.pikachu.user.userrole.service.PikaUserRoleReadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Slf4j
public class PikachuAuthInterceptor implements HandlerInterceptor {

    @Reference
    private PikaUserRoleReadService pikaUserRoleReadService;
    @Reference
    private PikaUserReadService pikaUserReadService;

    /**
     * 鉴权 判断用户是否有相应的权限
     *
     * @param request
     * @param response
     * @param handler
     * @return
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        // 筛选非http请求
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Auth auth = AnnotationUtils.getAnnotation(handlerMethod.getMethod(), Auth.class);

        //未使用 com.huiaong.pikachu.admin.annotation.Auth 标注的请求则不鉴权
        if (!Objects.isNull(auth)) {

            PikaBaseUser user = UserUtil.getCurrentUser();

            List<String> roleList = user.getRoles();

            String roleValue = auth.value();
            if (!roleList.contains(roleValue)) {
                Response<String> permissionRequireResp = Response.fail("permission require");
                response.setHeader("Content-type", "application/json;charset=UTF-8");
                try {
                    response.getWriter().write(JsonMapper.nonEmptyMapper().toJson(permissionRequireResp));
                } catch (IOException e) {
                    log.error("response 403 error, e{}", Throwables.getStackTraceAsString(e));
                }
                return false;
            }
        }
        return true;
    }
}
