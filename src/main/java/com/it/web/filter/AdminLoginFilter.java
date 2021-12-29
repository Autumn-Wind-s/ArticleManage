package com.it.web.filter;

import com.it.domain.Admin;
import com.it.domain.User;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = "/AdminIndex.jsp")
public class AdminLoginFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request1= (HttpServletRequest) request;
        HttpServletResponse response1= (HttpServletResponse) (HttpServletResponse) response;
        Admin admin = (Admin) request1.getSession().getAttribute("admin");
        if (admin==null){
            response1.sendRedirect("AdminLogin.jsp");
        }
        chain.doFilter(request, response);
    }
}
