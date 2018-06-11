package com.gmail.gm.jcant.JLinkManagement.JPA.Link;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gmail.gm.jcant.JLinkManagement.JPA.User.JUser;

public interface JLinkRepository  extends JpaRepository<JLink, Long> {
	@Query("SELECT l FROM JLink l WHERE l.url = :url")
    JLink findByUrl(@Param("url") String url);

    @Query("SELECT CASE WHEN COUNT(l) > 0 THEN true ELSE false END FROM JLink l WHERE l.url = :url")
    boolean existsByUrl(@Param("url") String url);
    
    @Query("SELECT l FROM JLink l WHERE l.user = :user")
    List<JLink> getLinksByUser(@Param("user") JUser user);
}
