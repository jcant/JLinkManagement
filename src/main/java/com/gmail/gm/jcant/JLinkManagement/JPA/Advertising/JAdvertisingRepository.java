package com.gmail.gm.jcant.JLinkManagement.JPA.Advertising;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface JAdvertisingRepository extends JpaRepository<JAdvertising, Long> {

    @Query("SELECT a FROM JAdvertising a WHERE a.company = :company")
    List<JAdvertising> getByCompany(@Param("company") String company);

    @Query("SELECT a FROM JAdvertising a WHERE (a.pubStart <= :date OR a.pubStart IS NULL) AND (a.pubFinish >= :date OR a.pubFinish IS NULL)")
    List<JAdvertising> getInDateAdvertising(@Param ("date") Date date);
}