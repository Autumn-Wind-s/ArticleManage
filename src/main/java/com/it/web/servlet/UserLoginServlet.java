package com.it.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.it.domain.ResultInfo;
import com.it.domain.User;
import com.it.service.UserService;
import com.it.service.impl.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "UserLoginServlet", value = "/UserLoginServlet")
public class UserLoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = new User();
        user.setUsername(request.getParameter("username"));
        user.setPassword(request.getParameter("password"));
        UserService us=new UserServiceImpl();
        String result=us.Login(user);
        ResultInfo resultInfo = new ResultInfo();
        if (result.equals("登录成功")){
            user=us.FindUser(user);
            request.getSession().setAttribute("user",user);
            request.getSession().setAttribute("articleCurrent","1");
            request.getSession().setAttribute("articleType","1");
            request.getSession().setAttribute("articleSelect","0");
            request.getSession().setAttribute("personalCurrent","1");
            request.getSession().setAttribute("personalType","1");

            resultInfo.setFlag(true);
        }
        else {
            if(result.equals("账号已冻结")){
                resultInfo.setFlag(false);
                resultInfo.setErrorMessage("账号已冻结，请联系管理员解冻！");
            }
            else {
                resultInfo.setFlag(false);
                resultInfo.setErrorMessage("用户名或密码错误，请重新输入！");
            }

        }
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(resultInfo);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       this.doGet(request,response);
    }
}
