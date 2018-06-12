package com.gmail.gm.jcant.JLinkManagement.JPA.Article;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gmail.gm.jcant.JLinkManagement.JPA.User.JUser;

public interface JArticleRepositiory extends JpaRepository<JArticle, Long> {
//	@Query("SELECT a FROM JArticle a JOIN lc.link l WHERE l.url = :url")     //is this good?!
//    List<JLinkClick> getByUrl(@Param("url") String url);

//    @Query("SELECT CASE WHEN COUNT(l) > 0 THEN true ELSE false END FROM JLink l WHERE l.url = :url")
//    boolean existsByUrl(@Param("url") String url);
    
    @Query("SELECT a FROM JArticle a WHERE a.author = :user")
    List<JArticle> getByUser(@Param("user") JUser user);
    
    @Query("SELECT a FROM JArticle a WHERE a.pubStart <= :date AND a.pubFinish >= :date")
    List<JArticle> getInDateArticles(@Param ("date") Date date);
}