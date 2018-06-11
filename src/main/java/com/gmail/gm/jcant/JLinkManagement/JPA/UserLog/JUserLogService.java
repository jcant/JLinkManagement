package com.gmail.gm.jcant.JLinkManagement.JPA.UserLog;

import java.util.List;

import com.gmail.gm.jcant.JLinkManagement.JPA.User.JUser;

public interface JUserLogService {
	//List<JLinkClick> getByUrl(String url);
    //boolean existsByUrl(String url);
    void addUserLog(JUserLog userLog);
    void updateUserLog(JUserLog userLog);
    List<JUserLog> getByUser(JUser user);
}
