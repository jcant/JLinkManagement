package com.gmail.gm.jcant.JLinkManagement.JPA.RootLink;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JRootLinkRepository extends JpaRepository<JRootLink, Long> {
	@Query("SELECT rl FROM JRootLink rl WHERE rl.url = :url")
	JRootLink findByUrl(@Param("url") String url);

	@Query("SELECT CASE WHEN COUNT(rl) > 0 THEN true ELSE false END FROM JRootLink rl WHERE rl.url = :url")
	boolean existsByUrl(@Param("url") String url);

	@Query("SELECT rl FROM JRootLink rl WHERE rl.enabled = :enabled")
	List<JRootLink> getByEnabled(@Param("enabled") boolean enabled);

}