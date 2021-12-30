package com.it.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.it.domain.Article;
import com.it.domain.PageBean;
import com.it.domain.User;
import com.it.service.ArticleService;
import com.it.service.impl.ArticleServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "PersonalSelectServlet", value = "/PersonalSelectServlet")
public class PersonalSelectServlet extends HttpServlet {
    private ArticleService articleService = new ArticleServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String currentPageStr = request.getParameter("currentPage");
        String pageSizeStr = request.getParameter("pageSize");
        String Type = request.getParameter("pageType");
        User user= (User) request.getSession().getAttribute("user");
        PageBean<Article> articlePageBean;
        if (Type != "") {
            request.getSession().setAttribute("PersonalArticleSelect", "1");
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
            request.getSession().setAttribute("personalCurrent",currentPageStr);
            request.getSession().setAttribute("personalType",Type);
            articlePageBean = articleService.PersonalSelectPage(currentPage,pageSize,Type,user.getUsername());
        } else {
            articlePageBean = new PageBean<Article>(0, 0, 0, 0, null);
        }
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(articlePageBean);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);
    }
}
