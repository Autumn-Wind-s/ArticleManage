package com.it.service.impl;

import com.it.dao.ArticleDao;
import com.it.dao.impl.ArticleDaoImpl;
import com.it.domain.Article;
import com.it.domain.PageBean;
import com.it.service.ArticleService;

public class ArticleServiceImpl implements ArticleService {
    private ArticleDao articleDao = new ArticleDaoImpl();

    @Override
    public PageBean<Article> pageQuery(int currentPage, int pageSize, String pageType) {
        PageBean<Article> pb = new PageBean<>();
        int start = (currentPage - 1) * pageSize;
        int totalPage;
        if (articleDao.findTotalCount(pageType) == 0) {
            totalPage = 0;
        } else {
            totalPage = articleDao.findTotalCount(pageType) % pageSize == 0 ? articleDao.findTotalCount(pageType) / pageSize : (articleDao.findTotalCount(pageType) / pageSize) + 1;
        }
        pb.setCurrentPage(currentPage);
        pb.setTotalCount(articleDao.findTotalCount(pageType));
        pb.setList(articleDao.findByPage(pageType, start, pageSize));
        pb.setPageSize(pageSize);
        pb.setTotalPage(totalPage);
        return pb;
    }

    public PageBean<Article> pageQuery(int currentPage, int pageSize, String pageType, String username) {
        PageBean<Article> pb = new PageBean<>();
        int start = (currentPage - 1) * pageSize;
        int totalPage;
        if (articleDao.findTotalCount(pageType, username) == 0) {
            totalPage = 0;
        } else {
            totalPage = articleDao.findTotalCount(pageType, username) % pageSize == 0 ? articleDao.findTotalCount(pageType,username) / pageSize : (articleDao.findTotalCount(pageType,username) / pageSize) + 1;
        }
        pb.setCurrentPage(currentPage);
        pb.setTotalCount(articleDao.findTotalCount(pageType, username));
        pb.setList(articleDao.findByPage(pageType, username, start, pageSize));
        pb.setPageSize(pageSize);
        pb.setTotalPage(totalPage);
        return pb;
    }

    public Article findText(String username, String articleName, String type) {
        return articleDao.findText(username, articleName, type);
    }

    @Override
    public boolean alterArticle(Article preArticle, Article lastArticle) {

        if (articleDao.alterArticle(preArticle, lastArticle) == 0) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean deleteArticle(Article article) {
        if (articleDao.deleteArticle(article) == 0) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean uploadArticle(Article article) {
        if (articleDao.insertArticle(article) == 0) {
            return false;
        }else {
            return true;
        }

    }

    @Override
    public PageBean<Article> selectPage(int currentPage, int pageSize, String pageType) {
        PageBean<Article> pb = new PageBean<>();
        int start = (currentPage - 1) * pageSize;
        int totalPage;
        if (articleDao.findVagueCount(pageType) == 0) {
            totalPage = 0;
        } else {
            totalPage = articleDao.findVagueCount(pageType) % pageSize == 0 ? articleDao.findVagueCount(pageType) / pageSize : (articleDao.findVagueCount(pageType) / pageSize) + 1;
        }
        pb.setCurrentPage(currentPage);
        pb.setTotalCount(articleDao.findVagueCount(pageType));
        pb.setList(articleDao.findVagueByPage(pageType,start,pageSize));
        pb.setPageSize(pageSize);
        pb.setTotalPage(totalPage);
        return pb;


    }
}
