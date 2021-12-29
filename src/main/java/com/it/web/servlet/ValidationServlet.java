package com.it.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.it.domain.ResultInfo;
import com.it.service.UserService;
import com.it.service.impl.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Enumeration;

@WebServlet(name = "ValidationServlet", value = "/ValidationServlet")
public class ValidationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String enrollUsername = request.getParameter("EnrollUsername");
        UserService userService = new UserServiceImpl();
        String s = userService.SendValidation(email,enrollUsername);
        ResultInfo resultInfo = new ResultInfo();
        if(s.equals("邮箱错误")){
        resultInfo.setFlag(false);
        resultInfo.setErrorMessage("邮箱错误");
        }
        else {
            request.getSession().setAttribute("validation",s);
            request.getSession().setAttribute("EnrollUsername",enrollUsername);
            resultInfo.setFlag(true);
        }
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(resultInfo);
        response.setContentType("appli90" +
                "cation/json;charset=utf-8");
        response.getWriter().write(json);
    }
}
