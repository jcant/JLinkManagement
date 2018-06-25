package com.gmail.gm.jcant.JLinkManagement.JPA.LinkClick;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gmail.gm.jcant.JLinkManagement.JPA.Link.JLink;

public interface JLinkClickRepository extends JpaRepository<JLinkClick, Long> {
//	@Query("SELECT lc, l FROM JLinkClick lc JOIN lc.link l WHERE l.url = :url")     //is this good?!
//    List<JLinkClick> getByUrl(@Param("url") String url);

//    @Query("SELECT CASE WHEN COUNT(l) > 0 THEN true ELSE false END FROM JLink l WHERE l.url = :url")
//    boolean existsByUrl(@Param("url") String url);
    
//    @Query("SELECT lc FROM JLinkClick lc JOIN lc.link l WHERE l.user = :user")
//    List<JLinkClick> getByUser(@Param("user") JUser user);
    
    @Query("SELECT count(lc) FROM JLinkClick lc WHERE lc.link = :link AND (:date = null OR lc.date>=:date)")
    int countLinkToDate(@Param("link") JLink link, @Param("date") Date date);
    
}
