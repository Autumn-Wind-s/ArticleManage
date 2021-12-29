package com.it.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.it.domain.Article;
import com.it.domain.ResultInfo;
import com.it.service.ArticleService;
import com.it.service.impl.ArticleServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "DeleteArticleServlet", value = "/DeleteArticleServlet")
public class DeleteArticleServlet extends HttpServlet {
    ArticleService articleService=new ArticleServiceImpl();
    ResultInfo resultInfo = new ResultInfo();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

      this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Article article = new Article();
       article.setUsername(request.getParameter("username"));
       article.setArticleName(request.getParameter("articleName"));
       article.setType(request.getParameter("type"));
       boolean flag=articleService.deleteArticle(article);
       if (flag){
           resultInfo.setFlag(true);
       }else {
           resultInfo.setFlag(false);
           resultInfo.setErrorMessage("删除失败!");
       }
        ObjectMapper objectMapper = new ObjectMapper();
       String json=objectMapper.writeValueAsString(resultInfo);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);
    }
}
