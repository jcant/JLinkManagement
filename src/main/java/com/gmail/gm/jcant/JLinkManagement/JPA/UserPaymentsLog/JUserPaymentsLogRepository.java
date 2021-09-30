package com.gmail.gm.jcant.JLinkManagement.JPA.UserPaymentsLog;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gmail.gm.jcant.JLinkManagement.JPA.User.JUser;

public interface JUserPaymentsLogRepository extends JpaRepository<JUserPaymentsLog, Long> {

	@Query("SELECT up FROM JUserPaymentsLog up WHERE up.user = :user")
	List<JUserPaymentsLog> getByUser(@Param("user") JUser user);
}
