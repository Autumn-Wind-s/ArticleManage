package com.it.domain;

import javax.xml.soap.Text;

public class Article {
    private String username;
    private String articleName;
    private String text;
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Article(String username, String articleName, String text, String type) {
        this.username = username;
        this.articleName = articleName;
        this.text = text;
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getArticleName() {
        return articleName;
    }

    public void setArticleName(String articleName) {
        this.articleName = articleName;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Article() {
    }

    public Article(String username, String articleName, String text) {
        this.username = username;
        this.articleName = articleName;
        this.text = text;
    }
}
