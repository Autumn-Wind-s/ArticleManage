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

@WebServlet(name = "EnrollServlet", value = "/EnrollServlet")
public class EnrollServlet extends HttpServlet {
    ResultInfo resultInfo = new ResultInfo();
    UserService us = new UserServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println(request.getHeader("referer"));
        String EnrollUsername  = request.getParameter("username");;
        User user = new User(request.getParameter("username"),request.getParameter("password"),request.getParameter("email"));

        if (request.getHeader("referer").equals("http://localhost:8085/ArticleManage/UserEnroll.jsp")){
            String validation = request.getParameter("validation");
            if (request.getSession().getAttribute("EnrollUsername")!=null){
            if(user.getUsername().equals( request.getSession().getAttribute("EnrollUsername"))){
                if (validation.equals(request.getSession().getAttribute("validation"))){
                    request.getSession().removeAttribute("validation");
                    request.getSession().removeAttribute("EnrollUsername");
                    boolean flag=us.Enroll(user);
                    if (flag){
                        resultInfo.setFlag(true);
                    }
                    else {
                        resultInfo.setFlag(false);
                        resultInfo.setErrorMessage("用户名重复！请更换用户名并重新验证。");
                    }

                }else {
                    resultInfo.setFlag(false);
                    resultInfo.setErrorMessage("验证码错误！");
                }

            }else {resultInfo.setFlag(false);
                resultInfo.setErrorMessage("用户名不一致！请更换用户名并重新验证。");}
        }
        else {
                resultInfo.setFlag(false);
                resultInfo.setErrorMessage("请先发送验证码！");

        }}
        else {
                    boolean flag=us.Enroll(user);
                    if (flag){
                        resultInfo.setFlag(true);
                    }
                    else {
                        resultInfo.setFlag(false);
                        resultInfo.setErrorMessage("用户名重复！请更换用户名并重新验证。");
                    }



            }
//
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(resultInfo);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);
          }
}
