package com.it.web.servlet;

import com.it.domain.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "AdminAlterUserServlet", value = "/AdminAlterUserServlet")
public class AdminAlterUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       request.setCharacterEncoding("utf-8");
        User user = new User(request.getParameter("username"),request.getParameter("password"),request.getParameter("email"),request.getParameter("status"));
        request.getSession().setAttribute("user",user);
        request.setAttribute("path","admin");
        request.getRequestDispatcher("AlterUser.jsp").forward(request,response);
    }
}
