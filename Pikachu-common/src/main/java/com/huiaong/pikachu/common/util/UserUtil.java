package com.huiaong.pikachu.common.util;

import com.huiaong.pikachu.common.base.model.PikaBaseUser;

public final class UserUtil {
    private static final ThreadLocal<PikaBaseUser> user = new ThreadLocal<>();

    public UserUtil() {
    }

    public static void putCurrentUser(PikaBaseUser baseUser) {
        user.set(baseUser);
    }

    public static PikaBaseUser getCurrentUser() {
        return user.get();
    }

    public static void clearCurrentUser() {
        user.remove();
    }

    public static Long getUserId() {
        PikaBaseUser baseUser = user.get();
        return null != baseUser ? baseUser.getId() : null;
    }
}
