package com.it.dao.impl;

import com.it.dao.ArticleDao;
import com.it.domain.Article;
import com.it.util.JdbcUtils;
import org.junit.Test;
import org.springframework.dao.DataAccessException;
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
        int count = 0;
        try {
            count = template.queryForObject(sql, Integer.class, type);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return count;
    }

    public int findTotalCount(String type, String username) {
        String sql = "select count(*) from user_article where type =? and username= ?";
        int count = 0;
        try {
            count = template.queryForObject(sql, Integer.class, type, username);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public List<Article> findByPage(String type, int start, int pageSize) {
        String sql = "select * from user_article where type=? limit ?,?";
        try {
            return template.query(sql, new BeanPropertyRowMapper<Article>(Article.class), type, start, pageSize);
        } catch (DataAccessException e) {
            return null;
        }
    }


    public List<Article> findByPage(String type, String username, int start, int pageSize) {
        String sql = "select * from user_article where type=? and username= ? limit ?,?";
        try {
            return template.query(sql, new BeanPropertyRowMapper<Article>(Article.class), type, username, start, pageSize);
        } catch (DataAccessException e) {
            return null;
        }
    }

    public Article findText(String username, String articleName, String type) {
        String sql = "select * from user_article where username =? and articlename=? and type =?";
        try {
            return template.queryForObject(sql, new BeanPropertyRowMapper<Article>(Article.class), username, articleName, type);
        } catch (DataAccessException e) {
            return null;
        }

    }

    @Override
    public int alterArticle(Article preArticle, Article lastArticle) {
        String sql = "update user_article set articlename=?,text=?,type=? where articlename=? and text=? and type=?  ";
        try {
            return template.update(sql, lastArticle.getArticleName(), lastArticle.getText(), lastArticle.getType(), preArticle.getArticleName(), preArticle.getText(), preArticle.getType());
        } catch (DataAccessException e) {
            return 0;
        }

    }

    @Override
    public int deleteArticle(Article article) {
        String sql = "delete from user_article where username=? and articlename= ? and type= ? ";
        try {
            return template.update(sql, article.getUsername(), article.getArticleName(), article.getType());
        } catch (DataAccessException e) {
            return 0;
        }

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
        int count ;
        try {
            count = template.queryForObject(sql, Integer.class, "%" + type + "%");
        } catch (DataAccessException e) {
            count=0;
        }

        return count;
    }

    @Override
    public int findPersonalVagueCount(String type, String username) {
        String sql = "select count(*) from user_article where username = ? and articlename like ?";

        int count = 0;
        try {
            count = template.queryForObject(sql, Integer.class,username, "%" + type + "%");
        } catch (DataAccessException e) {
            return 0;
        }
        return count;
    }

    @Override
    public List<Article> findVagueByPage(String type, int start, int pageSize) {
        String sql = "select * from user_article where articlename like  ? limit ?,?";
        try {
            return template.query(sql, new BeanPropertyRowMapper<Article>(Article.class), "%" + type + "%", start, pageSize);
        } catch (DataAccessException e) {
            return null;
        }

    }

    @Override
    public List<Article> findPersonalVagueByPage(String type, int start, int pageSize, String username) {
        String sql = "select * from user_article where username= ? and articlename like  ? limit ?,?";
        try {
            return template.query(sql, new BeanPropertyRowMapper<Article>(Article.class), username,"%" + type + "%", start, pageSize);
        } catch (DataAccessException e) {
            return null;
        }


    }

    @Test
    public void test() throws IOException {
        String sql = "select count(*) from user_article where articlename like ?";
        String s = "2";
        int count = template.queryForObject(sql, Integer.class, "%" + s + "%");
        System.out.println(count);
    }
}
