package com.it.dao.impl;

import com.it.dao.ArticleDao;
import com.it.domain.Article;
import com.it.util.JdbcUtils;
import org.junit.Test;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ArticleDaoImpl implements ArticleDao {
    private JdbcTemplate template = new JdbcTemplate(JdbcUtils.getDs());

    @Override
    public int findTotalCount(String type) {
        String sql = "select count(*) from user_article where type =? ";
        int count = template.queryForObject(sql, Integer.class, type);
        return count;
    }

    public int findTotalCount(String type, String username) {
        String sql = "select count(*) from user_article where type =? and username= ?";
        int count = template.queryForObject(sql, Integer.class, type, username);
        return count;
    }

    @Override
    public List<Article> findByPage(String type, int start, int pageSize) {
        String sql = "select * from user_article where type=? limit ?,?";
        return template.query(sql, new BeanPropertyRowMapper<Article>(Article.class), type, start, pageSize);
    }


    public List<Article> findByPage(String type, String username, int start, int pageSize) {
        String sql = "select * from user_article where type=? and username= ? limit ?,?";
        return template.query(sql, new BeanPropertyRowMapper<Article>(Article.class), type, username, start, pageSize);
    }

    public Article findText(String username, String articleName, String type) {
        String sql = "select * from user_article where username =? and articlename=? and type =?";
        return template.queryForObject(sql, new BeanPropertyRowMapper<Article>(Article.class), username, articleName, type);

    }

    @Override
    public int alterArticle(Article preArticle, Article lastArticle) {
        String sql = "update user_article set articlename=?,text=?,type=? where articlename=? and text=? and type=?  ";
        return template.update(sql, lastArticle.getArticleName(), lastArticle.getText(), lastArticle.getType(), preArticle.getArticleName(), preArticle.getText(), preArticle.getType());

    }

    @Override
    public int deleteArticle(Article article) {
        String sql = "delete from user_article where username=? and articlename= ? and type= ? ";
        return template.update(sql, article.getUsername(), article.getArticleName(), article.getType());

    }

    @Override
    public int insertArticle(Article article) {
        String sql = null;
        int i;
        try {
            sql = "insert into user_article(username,articlename,text,type) values(?,?,?,?) ";
            i = template.update(sql, article.getUsername(), article.getArticleName(), article.getText(), article.getType());

        } catch (Exception e) {
            i = 0;

        }

        return i;
    }

    @Override
    public int findVagueCount(String type) {
        String sql = "select count(*) from user_article where articlename like ?";
        int count = template.queryForObject(sql, Integer.class, "%" + type + "%");

        return count;
    }

    @Override
    public List<Article> findVagueByPage(String type, int start, int pageSize) {
        String sql = "select * from user_article where articlename like  ? limit ?,?";
        return template.query(sql, new BeanPropertyRowMapper<Article>(Article.class), "%" + type + "%", start, pageSize);

    }

    @Test
    public void test() throws IOException {
        String sql = "select count(*) from user_article where articlename like ?";
        String s = "2";
        int count = template.queryForObject(sql, Integer.class, "%" + s + "%");
        System.out.println(count);
    }
}
