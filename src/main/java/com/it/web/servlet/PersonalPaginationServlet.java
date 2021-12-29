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

@WebServlet(name = "PersonalPaginationServlet", value = "/PersonalPaginationServlet")
public class PersonalPaginationServlet extends HttpServlet {
    private ArticleService articleService= new ArticleServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String currentPageStr = request.getParameter("currentPage");
        String pageSizeStr = request.getParameter("pageSize");
        User user= (User) request.getSession().getAttribute("user");
        String pageType = "议论文";
        String Type = request.getParameter("pageType");
        switch (Type) {
            case "1":
                pageType = "议论文";
                break;
            case "2":
                pageType = "散文";
                break;
            case "3":
                pageType = "说明文";
                break;
            case "4":
                pageType = "记叙文";
                break;
            case "5":
                pageType = "小说";
                break;
            case "6":
                pageType = "诗歌";
                break;
            case "7":
                pageType = "应用文";
                break;
        }
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
        PageBean<Article> articlePageBean = articleService.pageQuery(currentPage, pageSize, pageType,user.getUsername());
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(articlePageBean);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);
    }
}
