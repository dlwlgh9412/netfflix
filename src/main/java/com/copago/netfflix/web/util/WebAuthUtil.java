package com.copago.netfflix.web.util;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class WebAuthUtil {
    private static final String REFRESH_TOKEN_NAME = "refresh_token";
    public static void setRefreshToken(HttpServletResponse response, String path, String refreshToken) {
        Cookie cookie = new Cookie(REFRESH_TOKEN_NAME, refreshToken);
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setPath(path);
        response.addCookie(cookie);
    }

    public static void deleteRefreshToken(HttpServletResponse response, String path) {
        Cookie cookie = new Cookie(REFRESH_TOKEN_NAME, null);
        cookie.setPath(path);
        cookie.setHttpOnly(true);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }
}
