package com.gmail.gm.jcant.JLinkManagement.JPA.UserPaymentsLog;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gmail.gm.jcant.JLinkManagement.JPA.User.JUser;

public interface JUserPaymentsLogRepository extends JpaRepository<JUserPaymentsLog, Long> {
//	@Query("SELECT lc FROM JLinkClick lc WHERE lc.JLink.url = :url")
//    List<JLinkClick> getByUrl(@Param("url") String url);

//    @Query("SELECT CASE WHEN COUNT(l) > 0 THEN true ELSE false END FROM JLink l WHERE l.url = :url")
//    boolean existsByUrl(@Param("url") String url);
    
    @Query("SELECT up FROM JUserPaymentsLog up WHERE up.user = :user")
    List<JUserPaymentsLog> getByUser(@Param("user") JUser user);
}
