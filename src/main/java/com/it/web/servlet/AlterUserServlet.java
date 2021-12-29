package com.it.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.it.domain.ResultInfo;
import com.it.domain.User;
import com.it.service.UserService;
import com.it.service.impl.UserServiceImpl;
import com.sun.org.apache.regexp.internal.RE;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "AlterUserServlet", value = "/AlterUserServlet")
public class AlterUserServlet extends HttpServlet {
    ResultInfo resultInfo = new ResultInfo();
    UserService userService=new UserServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       request.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        User preUser= (User) request.getSession().getAttribute("user");
        if (request.getParameter("prePassword").equals(preUser.getPassword())) {
            User newUser = new User(request.getParameter("username"), request.getParameter("newPassword"), request.getParameter("email"));
            boolean b = userService.updateUser(preUser, newUser);
            if (b){
                resultInfo.setFlag(true);
                request.getSession().setAttribute("user",newUser);
            }else {
                resultInfo.setFlag(false);
                resultInfo.setErrorMessage("用户名已存在，请更换用户名！");
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
