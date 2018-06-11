package com.gmail.gm.jcant.JLinkManagement.JPA.LinkClick;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gmail.gm.jcant.JLinkManagement.JPA.User.JUser;

public interface JLinkClickRepository extends JpaRepository<JLinkClick, Long> {
	@Query("SELECT lc, l FROM JLinkClick lc JOIN lc.link l WHERE l.url = :url")     //is this good?!
    List<JLinkClick> getByUrl(@Param("url") String url);

//    @Query("SELECT CASE WHEN COUNT(l) > 0 THEN true ELSE false END FROM JLink l WHERE l.url = :url")
//    boolean existsByUrl(@Param("url") String url);
    
    @Query("SELECT lc, l FROM JLinkClick lc JOIN lc.link l WHERE l.user = :user")
    List<JLinkClick> getByUser(@Param("user") JUser user);
}
