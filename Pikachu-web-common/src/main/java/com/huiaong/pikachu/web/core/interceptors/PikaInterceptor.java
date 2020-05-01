package com.huiaong.pikachu.web.core.interceptors;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Objects;

public class PikaInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(false);
        if (!Objects.isNull(session)) {
            Object userIdInSession = session.getAttribute("userId");
            if (userIdInSession != null) {
                Long userId = Long.valueOf(userIdInSession.toString());
            }
        }
        return true;
    }
}
