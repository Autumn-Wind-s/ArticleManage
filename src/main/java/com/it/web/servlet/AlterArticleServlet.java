package com.it.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.it.domain.Article;
import com.it.domain.ResultInfo;
import com.it.domain.User;
import com.it.service.ArticleService;
import com.it.service.impl.ArticleServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "AlterArticleServlet", value = "/AlterArticleServlet")
public class AlterArticleServlet extends HttpServlet {
    ArticleService articleService=new ArticleServiceImpl();
    ResultInfo resultInfo = new ResultInfo();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       Article lastArticle = new Article();
       Article preArticle= (Article) request.getSession().getAttribute("article");
       lastArticle.setArticleName(request.getParameter("articleName"));
        lastArticle.setText(request.getParameter("text"));
        lastArticle.setType(request.getParameter("type"));
        lastArticle.setUsername(lastArticle.getUsername());
        boolean flag=articleService.alterArticle(preArticle,lastArticle);
       if (flag){
        resultInfo.setFlag(true);
        request.getSession().setAttribute("article",lastArticle);
    }else {
       resultInfo.setFlag(false);
       resultInfo.setErrorMessage("信息有误，请重新修改");
       }
        ObjectMapper objectMapper = new ObjectMapper();
       String json=objectMapper.writeValueAsString(resultInfo);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);
    }
}
