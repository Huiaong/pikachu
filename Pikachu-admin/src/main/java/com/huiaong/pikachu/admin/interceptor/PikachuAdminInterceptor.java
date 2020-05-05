package com.huiaong.pikachu.admin.interceptor;

import com.huiaong.pikachu.admin.annotation.Auth;
import com.huiaong.pikachu.common.response.Response;
import com.huiaong.pikachu.user.userrole.service.PikaUserRoleReadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Objects;

@Slf4j
public class PikachuAdminInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private PikaUserRoleReadService pikaUserRoleReadService;

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
        HttpSession session = request.getSession(false);

        // 筛选非http请求
        if (Objects.isNull(session) || !(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Auth auth = AnnotationUtils.getAnnotation(handlerMethod.getMethod(), Auth.class);

        //未使用 com.huiaong.pikachu.admin.annotation.Auth 标注的请求则不鉴权
        if (!Objects.isNull(auth)) {
            Object userIdInSession = session.getAttribute("userId");
            if (userIdInSession != null) {
                Long userId = Long.valueOf(userIdInSession.toString());
                Response<List<String>> roleListResp = pikaUserRoleReadService.findRolesByUserId(userId);
                if (!roleListResp.isSuccess()) {
                    log.error("find user role by user id:{} failed, cause by:{}", userId, roleListResp.getError());
                    return false;
                }
                String roleValue = auth.value();
                List<String> roleList = roleListResp.getResult();
                return roleList.contains(roleValue);
            }
        }
        return true;
    }
}
