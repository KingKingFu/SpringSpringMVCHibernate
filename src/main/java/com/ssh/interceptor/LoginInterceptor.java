package com.ssh.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

//        if (o.toString().contains("login"))
//            return true;
//
//        HttpSession session = httpServletRequest.getSession(false);
//        if (session == null)
//            return false;
//
//        Object user = session.getAttribute("user");
//
//        if (user == null) {
//            httpServletRequest.getRequestDispatcher("/login.jsp").forward(httpServletRequest, httpServletResponse);
//            return false;
//        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
    }
}
