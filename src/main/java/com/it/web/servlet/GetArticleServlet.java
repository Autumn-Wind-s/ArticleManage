package com.it.web.servlet;

import com.it.domain.Article;
import com.it.service.ArticleService;
import com.it.service.impl.ArticleServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "GetArticleServlet", value = "/GetArticleServlet")
public class GetArticleServlet extends HttpServlet {
    ArticleService articleService = new ArticleServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Article article = new Article();
        request.setCharacterEncoding("utf-8");
        article = articleService.findText(request.getParameter("username"), request.getParameter("articleName"), request.getParameter("type"));
        System.out.println(article.getText());
        request.getSession().setAttribute("article", article);
        response.sendRedirect("AlterArticle.jsp");
    }
}
