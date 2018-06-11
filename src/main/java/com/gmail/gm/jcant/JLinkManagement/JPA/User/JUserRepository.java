package com.gmail.gm.jcant.JLinkManagement.JPA.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface JUserRepository extends JpaRepository<JUser, Long> {
    @Query("SELECT u FROM JUser u WHERE u.login = :login")
    JUser findByLogin(@Param("login") String login);

    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM JUser u WHERE u.login = :login")
    boolean existsByLogin(@Param("login") String login);
}
