package com.kuang.config;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//自定义拦截器
//防止绕过登录界面直接进入后台管理界面，判断session中的username是否存在，然后将拦截器注册到我们的SpringMVC配置类当中！
public class LoginHandlerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取 loginUser 信息进行判断 session信息在登录已经存在
        Object user = request.getSession().getAttribute("loginUser");
        if(user == null){//未登录，返回登录页面
            request.setAttribute("msg","没有权限，请先登录");
            request.getRequestDispatcher("/index.html").forward(request,response);
            return false;
        }else{
            //登录，放行
            return true;
        }
    }
}
