package com.it.web.servlet;

import com.it.domain.Article;
import com.it.service.ArticleService;
import com.it.service.impl.ArticleServiceImpl;
import com.it.util.DownLoadUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "DownLoadServlet", value = "/DownLoadServlet")
public class DownLoadServlet extends HttpServlet {
    ArticleService articleService=new ArticleServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Article article = new Article();
        request.setCharacterEncoding("utf-8");
        article=articleService.findText(request.getParameter("username"),request.getParameter("articleName"),request.getParameter("type"));
        response.setHeader("content-type",  "application/vnd.openxmlformats-officedocument.wordprocessingml.document");
        String name=article.getArticleName();
        String agent=request.getHeader("user-agent");
        name= DownLoadUtils.getFileName(agent,name);
        response.setHeader("content-disposition","attachment;filename="+name+".docx");
        ServletOutputStream outputStream = response.getOutputStream();
        byte[] sb;
        String articleName=article.getArticleName()+"\n";
        String username="作者:"+article.getUsername()+"\n";
        sb=articleName.getBytes(StandardCharsets.UTF_8);
        outputStream.write(sb);
        sb=username.getBytes(StandardCharsets.UTF_8);
        outputStream.write(sb);
        sb=article.getText().getBytes(StandardCharsets.UTF_8);
        outputStream.write(sb);
        outputStream.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    this.doGet(request,response);
    }
}
