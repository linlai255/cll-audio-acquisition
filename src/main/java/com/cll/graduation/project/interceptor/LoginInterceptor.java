package com.cll.graduation.project.interceptor;

import com.cll.graduation.project.vo.CurrentUser;
import com.cll.graduation.project.vo.LoginUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("request: {}", request);
        log.info("response: {}", response);
        //String token = request.getHeader("token");
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI4ODgiLCJzdWIiOiLlsI_nmb0iLCJpYXQiOjE2NTAwMTUyNjgsImV4cCI6MTY1MDIxMTIyOH0.3fbTI8HPh1wHNALHG-4NLcAhLK_UZtroV1Sllwa9tfw";
        try {
            Claims claims = Jwts.parser().setSigningKey( "CheLinLai" ).parseClaimsJws( token ).getBody();
            LoginUser loginUser = new LoginUser();
            loginUser.setEmail("13890697926@163.com");
            loginUser.setName("车林来");
            loginUser.setToken(token);
            CurrentUser.set(loginUser);
        } catch (Exception e) {
            log.error("token parse fail: {}", e);
            throw new IllegalArgumentException("请重新登录");
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        CurrentUser.remove();
        return;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        return;
    }
}
