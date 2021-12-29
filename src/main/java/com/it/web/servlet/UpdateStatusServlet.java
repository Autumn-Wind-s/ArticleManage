package com.it.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.it.domain.ResultInfo;
import com.it.service.UserService;
import com.it.service.impl.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "UpdateStatusServlet", value = "/UpdateStatusServlet")
public class UpdateStatusServlet extends HttpServlet {
    UserService userService=new UserServiceImpl();
    ResultInfo resultInfo=new ResultInfo();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        boolean b;
        if (request.getParameter("status").equals("解冻"))
        {
            b = userService.updateStatus(request.getParameter("username"), request.getParameter("password"), "正常");
        }else {
            b = userService.updateStatus(request.getParameter("username"), request.getParameter("password"), "冻结");
        }
        if(b){
            resultInfo.setFlag(true);
        }else {
            resultInfo.setFlag(false);
        }
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(resultInfo);
        response.getWriter().write(json);
    }
}
