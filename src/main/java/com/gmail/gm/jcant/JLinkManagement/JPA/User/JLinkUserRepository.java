package com.gmail.gm.jcant.JLinkManagement.JPA.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface JLinkUserRepository extends JpaRepository<JLinkUser, Long> {
    @Query("SELECT u FROM JLinkUser u WHERE u.login = :login")
    JLinkUser findByLogin(@Param("login") String login);

    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM JLinkUser u WHERE u.login = :login")
    boolean existsByLogin(@Param("login") String login);

    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM JLinkUser u WHERE u.id = :id")
    boolean existsById(@Param("id") long id);
}
