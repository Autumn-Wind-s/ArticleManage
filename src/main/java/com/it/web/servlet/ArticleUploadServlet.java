package com.it.web.servlet;

import com.it.domain.Article;
import com.it.domain.User;
import com.it.service.ArticleService;
import com.it.service.impl.ArticleServiceImpl;
import com.it.util.DocxUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

@WebServlet(name = "ArticleUploadServlet", value = "/ArticleUploadServlet")
public class ArticleUploadServlet extends HttpServlet {
    ArticleService articleService = new ArticleServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        PrintWriter writer = response.getWriter();
        String articleName = null;
        String type = null;
        String text = null;
        User user = (User) request.getSession().getAttribute("user");
        boolean multipartContent = ServletFileUpload.isMultipartContent(request);
        System.out.println(multipartContent);
        FileItemFactory diskFileItemFactory = new DiskFileItemFactory();
        ServletFileUpload Upload = new ServletFileUpload(diskFileItemFactory);
        List<FileItem> fileItems = null;
        try {
            fileItems = Upload.parseRequest(request);
        } catch (FileUploadException e) {
            writer.write("文件解析失败,<a href=\"UpLoadArticle.jsp\">点击返回</a>\n");
        }
        Iterator<FileItem> iterator = fileItems.iterator();
        while (iterator.hasNext()) {
            FileItem next = iterator.next();
            if (next.isFormField()) {
                if (next.getFieldName().equals("articleName")) {
                    articleName = next.getString("utf-8");
                } else if (next.getFieldName().equals("type")) {
                    type = next.getString("utf-8");
                }
            } else {
                String fileUpName=next.getName();
                String Suffix=fileUpName.substring(fileUpName.lastIndexOf("."));
                if (".docx".equals(Suffix)){
                File file=new File("src/main/webapp/docx/"+fileUpName);
                String filepath=file.getPath();
                if (!file.exists()){
                    file.createNewFile();
                }
                    try {
                        next.write(file);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    text = DocxUtils.dealDocXFile(filepath);
                    boolean delete = file.delete();
                    System.out.println(delete);
                }else
                {text = next.getString("utf-8");}
            }
        }

        Article article = new Article(user.getUsername(), articleName, text, type);
        boolean flag = articleService.uploadArticle(article);
        if (flag)
        {  writer.write("上传成功,<a href=\"UpLoadArticle.jsp\">点击返回</a>\n");
    }else {
            writer.write("上传失败，文章已存在,<a href=\"UpLoadArticle.jsp\">点击返回</a>\n");

        }
    }
}
