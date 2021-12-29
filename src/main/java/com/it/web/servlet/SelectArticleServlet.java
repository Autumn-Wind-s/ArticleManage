package com.it.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.it.domain.Article;
import com.it.domain.PageBean;
import com.it.domain.ResultInfo;
import com.it.service.ArticleService;
import com.it.service.impl.ArticleServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "SelectArticleServlet", value = "/SelectArticleServlet")
public class SelectArticleServlet extends HttpServlet {
    private ArticleService articleService= new ArticleServiceImpl();
    private ResultInfo resultInfo=new ResultInfo();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String currentPageStr = request.getParameter("currentPage");
        String pageSizeStr = request.getParameter("pageSize");
        String Type  =request.getParameter("pageType");
        PageBean<Article> articlePageBean;
        if (Type!="") {
            request.getSession().setAttribute("articleSelect", "1");
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
            request.getSession().setAttribute("articleCurrent", currentPageStr);
            request.getSession().setAttribute("articleType", Type);
             articlePageBean = articleService.selectPage(currentPage, pageSize, Type);
        }else{
             articlePageBean =new PageBean<Article>(0,0,0,0,null);
        }
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(articlePageBean);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);
    }
}
