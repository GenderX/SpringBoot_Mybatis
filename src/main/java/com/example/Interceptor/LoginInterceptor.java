package com.example.Interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author ：LiCan
 * @date ：Created in 2019/1/9 15:24
 * @description：${description}
 * @modified By：
 * @version: $version$
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 检查每个到来的请求对应的session域中是否有登录标识
        Object loginName = request.getSession().getAttribute("user");
        if (null == loginName) {
            // 未登录，重定向到登录页
            response.sendRedirect("/login.jsp");
            return false;
        }
        return true;

    }
}
