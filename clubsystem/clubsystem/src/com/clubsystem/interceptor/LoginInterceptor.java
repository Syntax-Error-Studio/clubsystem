package com.clubsystem.interceptor;

import com.clubsystem.bean.User;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 登录拦截器，用于限制未登录用户访问学生和管理员业务页面。
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        if (uri.endsWith("/login") || uri.endsWith("/register") || uri.endsWith("/logout") || uri.endsWith("/index") || uri.endsWith("/") || uri.endsWith("/index.jsp")) {
            return true;
        }

        HttpSession session = request.getSession(false);
        User currentUser = session == null ? null : (User) session.getAttribute("currentUser");
        if (currentUser == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return false;
        }

        if (uri.contains("/admin/") && !("admin".equals(currentUser.getRole()) || "superadmin".equals(currentUser.getRole()))) {
            response.sendRedirect(request.getContextPath() + "/student/index.jsp");
            return false;
        }

        return true;
    }
}

