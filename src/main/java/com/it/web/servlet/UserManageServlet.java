package com.it.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.it.domain.PageBean;
import com.it.domain.User;
import com.it.service.UserService;
import com.it.service.impl.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "UserManageServlet", value = "/UserManageServlet")
public class UserManageServlet extends HttpServlet {
    UserService userService=new UserServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String currentPageStr = request.getParameter("currentPage");
        String pageSizeStr = request.getParameter("pageSize");
        int currentPage = 0;
        int pageSize = 0;
        if (currentPageStr != null && currentPageStr.length() > 0) {
            currentPage = Integer.parseInt(currentPageStr);
        } else {
            currentPage = 1;
        }
        if (pageSizeStr != null && pageSizeStr.length() >= 0) {
            pageSize = Integer.parseInt(pageSizeStr);

        } else {
            pageSize = 5;
        }
        request.getSession().setAttribute("current",currentPageStr);
        PageBean<User> userPageBean = userService.pageQuery(currentPage, pageSize);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(userPageBean);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);
    }
}
