package com.it.dao;

import com.it.domain.Article;

import java.util.List;

public interface ArticleDao {
    public int findTotalCount(String type);
    public int findTotalCount(String type,String username);
    public List<Article> findByPage(String type,int start,int pageSize);
    public List<Article> findByPage(String type,String username,int start,int pageSize);
    public Article findText(String username,String articleName,String type);
    public int alterArticle(Article preArticle,Article lastArticle);
    public int deleteArticle(Article article);
    public int insertArticle(Article article);
    public  int findVagueCount(String type);
    public  List<Article> findVagueByPage(String type,int start,int pageSize);
}
