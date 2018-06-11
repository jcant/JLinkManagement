package com.gmail.gm.jcant.JLinkManagement.JPA.UserLog;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gmail.gm.jcant.JLinkManagement.JPA.User.JUser;

public interface JUserLogRepository extends JpaRepository<JUserLog, Long> {
//	@Query("SELECT lc FROM JLinkClick lc WHERE lc.JLink.url = :url")
//    List<JLinkClick> getByUrl(@Param("url") String url);

//    @Query("SELECT CASE WHEN COUNT(l) > 0 THEN true ELSE false END FROM JLink l WHERE l.url = :url")
//    boolean existsByUrl(@Param("url") String url);
    
    @Query("SELECT ul FROM JUserLog ul WHERE ul.user = :user")
    List<JUserLog> getByUser(@Param("user") JUser user);
}
