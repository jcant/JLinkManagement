package com.gmail.gm.jcant.JLinkManagement.JPA.Article;

import java.util.Date;
import java.util.List;

import com.gmail.gm.jcant.JLinkManagement.JPA.User.JUser;

public interface JArticleService {
	//List<JLinkClick> getByUrl(String url);
    //boolean existsByUrl(String url);
    void addArticle(JArticle article);
    void updateArticle(JArticle article);
    List<JArticle> getByUser(JUser user);
    List<JArticle> getInDateArticles(Date date);
}