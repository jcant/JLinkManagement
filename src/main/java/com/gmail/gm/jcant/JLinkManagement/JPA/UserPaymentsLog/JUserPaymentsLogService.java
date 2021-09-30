package com.gmail.gm.jcant.JLinkManagement.JPA.UserPaymentsLog;

import java.util.List;

import com.gmail.gm.jcant.JLinkManagement.JPA.Link.JLink;
import com.gmail.gm.jcant.JLinkManagement.JPA.User.JUser;

public interface JUserPaymentsLogService {

	List<JUserPaymentsLog> getByUser(JUser user);

	void SaveUserPayment(JUser user, JLink link, double amount, String paySystem) throws JUserPaymentsLogException;
}
