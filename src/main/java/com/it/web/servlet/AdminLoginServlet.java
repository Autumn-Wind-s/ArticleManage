package com.it.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.it.domain.Admin;
import com.it.domain.ResultInfo;
import com.it.service.AdminService;
import com.it.service.impl.AdminServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "AdminLoginServlet", value = "/AdminLoginServlet")
public class AdminLoginServlet extends HttpServlet {
    private AdminService adminService=new AdminServiceImpl();
    private ResultInfo resultInfo=new ResultInfo();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       request.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        Admin admin = new Admin(request.getParameter("name"),request.getParameter("password"));
        System.out.println(admin.getPassword()+admin.getUsername());
        boolean login = adminService.Login(admin);
        System.out.println(login);
        if (login){
            resultInfo.setFlag(true);
            request.getSession().setAttribute("admin",admin);
            request.getSession().setAttribute("current","1");
            request.getSession().setAttribute("articleCurrent","1");
            request.getSession().setAttribute("articleType","1");
            request.getSession().setAttribute("articleSelect","0");

        }else {
            resultInfo.setFlag(false);
            resultInfo.setErrorMessage("用户名或密码错误！");
        }
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(resultInfo);
        response.getWriter().write(json);
    }
}
