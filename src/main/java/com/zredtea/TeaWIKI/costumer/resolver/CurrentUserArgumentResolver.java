package com.zredtea.TeaWIKI.costumer.resolver;

import com.zredtea.TeaWIKI.DTO.response.UserDTO;
import com.zredtea.TeaWIKI.common.exception.AuthException;
import com.zredtea.TeaWIKI.common.exception.ExceptionEnum;
import com.zredtea.TeaWIKI.costumer.annotation.CurrentUser;
import com.zredtea.TeaWIKI.entity.User;
import com.zredtea.TeaWIKI.service.UserService;
import com.zredtea.TeaWIKI.util.JWTUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
public class CurrentUserArgumentResolver implements HandlerMethodArgumentResolver {
    private final JWTUtil jwtUtil;
    private final UserService userService;

    public CurrentUserArgumentResolver(JWTUtil jwtUtil, UserService userService) {
        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        if (!parameter.hasParameterAnnotation(CurrentUser.class)) {
            return false;
        }

        Class<?> parameterType = parameter.getParameterType();
        return Integer.class.equals(parameterType) ||
                String.class.equals(parameterType) ||
                User.class.equals(parameterType) ||
                UserDTO.class.equals(parameterType);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter,
                                  ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
        String token = getTokenFromRequest(request);

        if(token != null && jwtUtil.validateToken(token)) {
            Class<?> paramaterClass = parameter.getParameterType();
            if(paramaterClass.equals(Integer.class)) {
                return jwtUtil.getUserIdFromToken(token);
            }else if(paramaterClass.equals(String.class)) {
                return jwtUtil.getUsernameFromToken(token);
            }else if(paramaterClass.equals(User.class)) {
                return userService.getById(jwtUtil.getUserIdFromToken(token));
            }
        }

        CurrentUser currentUserAnnotation = parameter.getParameterAnnotation(CurrentUser.class);
        if(currentUserAnnotation != null && !currentUserAnnotation.required()) {
            return null;
        }
        throw new AuthException(ExceptionEnum.TOKEN_NOT_EQUAL);
    }

    private String getTokenFromRequest(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("access_token".equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }

        String authHeader  = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }

        return null;
    }
}
