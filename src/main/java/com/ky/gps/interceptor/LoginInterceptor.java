package com.ky.gps.interceptor;

import com.ky.gps.util.SysLogUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @author Daye
 * 登录拦截器
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {
    private final static String USER_LOGIN_URI = "/user/login";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        //放行uri
        if (uri.contains(USER_LOGIN_URI)
                || uri.contains("/user/modify/pwd/email")
                || uri.contains("/user/send/email")) {
            return true;
        } else if (request.getSession().getAttribute(SysLogUtil.SESSION_SYSLOG) == null
                && uri.contains("/user")) {
            //拦截未登录的非法操作
            response.sendRedirect(request.getContextPath() + "/realTime/login.html");
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
