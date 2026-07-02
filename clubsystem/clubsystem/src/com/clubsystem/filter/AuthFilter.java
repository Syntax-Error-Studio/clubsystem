package com.clubsystem.filter;

import com.clubsystem.bean.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 简单登录与角色权限过滤器。
 */
@WebFilter(urlPatterns = {"/admin/*", "/student/*"})
public class AuthFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        HttpSession session = req.getSession(false);
        User currentUser = null;
        if (session != null) {
            Object obj = session.getAttribute("currentUser");
            if (obj instanceof User) {
                currentUser = (User) obj;
            }
        }

        if (currentUser == null) {
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }

        String uri = req.getRequestURI();
        String role = currentUser.getRole();

        if (uri.contains("/admin/") && !("admin".equals(role) || "superadmin".equals(role))) {
            resp.sendRedirect(req.getContextPath() + "/student/index.jsp");
            return;
        }

        if (uri.contains("/student/") && "admin".equals(role)) {
            resp.sendRedirect(req.getContextPath() + "/admin/index.jsp");
            return;
        }

        chain.doFilter(request, response);
    }
}
