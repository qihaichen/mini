package com.ruochen.shop.configurations;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MyHandlerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        if(session.getAttribute("isLogin")!=null){
            return true;
        }

        else {
            session.setAttribute("msg","请先登录!!!");
            response.sendRedirect("/");
            return false;
        }

    }
}
