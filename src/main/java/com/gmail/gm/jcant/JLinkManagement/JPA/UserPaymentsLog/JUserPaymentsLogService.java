package com.gmail.gm.jcant.JLinkManagement.JPA.UserPaymentsLog;

import java.util.List;

import com.gmail.gm.jcant.JLinkManagement.JPA.User.JUser;

public interface JUserPaymentsLogService {
	//List<JLinkClick> getByUrl(String url);
    //boolean existsByUrl(String url);
    void addUserPaymentsLog(JUserPaymentsLog userPaymentsLog);
    void updateUserPaymentsLog(JUserPaymentsLog serPaymentsLog);
    List<JUserPaymentsLog> getByUser(JUser user);
}
