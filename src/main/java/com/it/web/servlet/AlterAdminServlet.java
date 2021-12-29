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

@WebServlet(name = "AlterAdminServlet", value = "/AlterAdminServlet")
public class AlterAdminServlet extends HttpServlet {
    AdminService adminService=new AdminServiceImpl();
    ResultInfo resultInfo=new ResultInfo();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        Admin preAdmin= (Admin) request.getSession().getAttribute("admin");
        Admin newAdmin = new Admin(request.getParameter("username"),request.getParameter("newPassword"));
    if (preAdmin.getPassword().equals(request.getParameter("prePassword"))){
        boolean b = adminService.updateAdmin(preAdmin, newAdmin);
        if (b){
            resultInfo.setFlag(true);
            request.getSession().setAttribute("admin",newAdmin);

        }else {
            resultInfo.setFlag(false);
            resultInfo.setErrorMessage("用户名已存在，请更换修改后的用户名！");
        }


    }
    else {
        resultInfo.setFlag(false);
        resultInfo.setErrorMessage("原密码错误");
    }
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(resultInfo);
        response.getWriter().write(json);
    }
}
