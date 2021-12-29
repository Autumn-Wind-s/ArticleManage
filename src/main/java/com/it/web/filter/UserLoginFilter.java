package com.it.web.filter;

import com.it.domain.User;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = "/index.jsp")
public class UserLoginFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request1= (HttpServletRequest) request;
        HttpServletResponse response1= (HttpServletResponse) (HttpServletResponse) response;
        User user = (User) request1.getSession().getAttribute("user");
        if (user==null){
            response1.sendRedirect("UserLogin.jsp");
        }
        chain.doFilter(request, response);
    }
}
