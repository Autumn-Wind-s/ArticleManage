package com.it.service;

import com.it.domain.Article;
import com.it.domain.PageBean;

public interface ArticleService {
    public PageBean<Article> pageQuery(int currentPage,int pageSize,String pageType);
    public PageBean<Article> pageQuery(int currentPage,int pageSize,String pageType,String username);
    public Article findText(String username,String articleName,String type);
    public boolean alterArticle(Article preArticle,Article lastArticle);
    public boolean deleteArticle(Article article);
    public  boolean uploadArticle(Article article);
    public PageBean<Article> selectPage(int currentPage,int pageSize,String pageType);
}

