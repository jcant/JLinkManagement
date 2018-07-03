package com.gmail.gm.jcant.JLinkManagement.JPA.Article;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gmail.gm.jcant.JLinkManagement.JPA.User.JUser;

public interface JArticleRepositiory extends JpaRepository<JArticle, Long> {

    @Query("SELECT a FROM JArticle a WHERE a.author = :user")
    List<JArticle> getByUser(@Param("user") JUser user);
    
    @Query("SELECT a FROM JArticle a WHERE (a.pubStart <= :date OR a.pubStart IS NULL) AND (a.pubFinish >= :date OR a.pubFinish IS NULL)")
    List<JArticle> getInDateArticles(@Param ("date") Date date);


}