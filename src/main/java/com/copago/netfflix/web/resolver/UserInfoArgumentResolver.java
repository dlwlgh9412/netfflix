package com.copago.netfflix.web.resolver;

import com.copago.netfflix.util.JwtProvider;
import com.copago.netfflix.web.annotation.AuthenticatedUser;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
@RequiredArgsConstructor
public class UserInfoArgumentResolver implements HandlerMethodArgumentResolver {
    private final JwtProvider jwtProvider;
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(AuthenticatedUser.class) && parameter.getParameterType().isAssignableFrom(com.copago.netfflix.dto.UserInfo.class);
    }

    @Override
    public Object resolveArgument(@NonNull MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        final String accessToken = webRequest.getHeader("Authorization");
        if (!StringUtils.hasText(accessToken)) throw new IllegalArgumentException("액세스 토큰이 존재하지 않습니다.");
        final Claims claims = jwtProvider.parseClaims(accessToken.replace("Bearer", ""));
        return new com.copago.netfflix.dto.UserInfo(Long.parseLong(claims.get("id").toString()));
    }
}
