package com.gmail.gm.jcant.JLinkManagement.JPA.Link;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gmail.gm.jcant.JLinkManagement.JPA.User.JUser;

public interface JLinkRepository  extends JpaRepository<JLink, Long> {
	//@Query("SELECT l FROM JLink l WHERE l.url = :url")
    //JLink findByUrl(@Param("url") String url);

    @Query("SELECT l FROM JLink l WHERE (l.url = :url AND l.enabled = true AND l.finishDate >= :date)")
    JLink getByUrlActualEnabled(@Param("url") String url, @Param("date") Date date);
	
	@Query("SELECT l FROM JLink l WHERE (l.user = :user AND (:date = null OR l.finishDate>=:date)) ORDER BY l.finishDate DESC")
    List<JLink> findByUserAll(@Param("user") JUser user, @Param("date") Date date);
	@Query("SELECT l FROM JLink l WHERE (l.user = :user AND l.free = true AND (:date = null OR l.finishDate>=:date)) ORDER BY l.finishDate DESC")
    List<JLink> findByUserFree(@Param("user") JUser user, @Param("date") Date date);
	@Query("SELECT l FROM JLink l WHERE l.user = :user AND l.free = false AND (:date = null OR l.finishDate>=:date) ORDER BY l.finishDate DESC")
    List<JLink> findByUserPaid(@Param("user") JUser user, @Param("date") Date date);

    @Query("SELECT CASE WHEN COUNT(l) > 0 THEN true ELSE false END FROM JLink l WHERE l.url = :url")
    boolean existsByUrl(@Param("url") String url);
    @Query("SELECT CASE WHEN COUNT(l) > 0 THEN false ELSE true END FROM JLink l WHERE (l.url = :url AND l.finishDate >= :date)")
    boolean isFreeByUrlOnDate(@Param("url") String url, @Param("date") Date date);
    
    

}
