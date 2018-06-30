package com.gmail.gm.jcant.JLinkManagement.JPA.UserPaymentsLog;

import java.util.List;

import com.gmail.gm.jcant.JLinkManagement.JPA.Link.JLink;
import com.gmail.gm.jcant.JLinkManagement.JPA.User.JUser;

import javax.servlet.http.HttpServletRequest;

public interface JUserPaymentsLogService {
	//List<JLinkClick> getByUrl(String url);
    //boolean existsByUrl(String url);
    //void addUserPaymentsLog(JUserPaymentsLog userPaymentsLog);
    //void updateUserPaymentsLog(JUserPaymentsLog serPaymentsLog);
    List<JUserPaymentsLog> getByUser(JUser user);

    void SaveUserPayment(JUser user, JLink link, double amount, String paySystem);
}
