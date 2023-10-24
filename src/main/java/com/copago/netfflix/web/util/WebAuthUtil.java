package com.copago.netfflix.web.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

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
