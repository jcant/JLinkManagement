package com.gmail.gm.jcant.JLinkManagement.JPA.UserPaymentsLog;

import java.util.Date;
import java.util.List;

import com.gmail.gm.jcant.JLinkManagement.JPA.Link.JLink;
import com.gmail.gm.jcant.JLinkManagement.JPA.UserLog.JUserLog;
import org.springframework.beans.factory.annotation.Autowired;

import com.gmail.gm.jcant.JLinkManagement.JPA.User.JUser;
import org.springframework.stereotype.Service;

@Service
public class JUserPaymentsLogServiceImpl implements JUserPaymentsLogService{

	@Autowired
    private JUserPaymentsLogRepository userPaymentsLogRepository;
	
//	@Override
//	public List<JLinkClick> getByUrl(String url) {
//		return linkClickRepository.getByUrl(url);
//	}

//	@Override
//	public void addUserPaymentsLog(JUserPaymentsLog userPaymentsLog) {
//		userLogRepository.save(userPaymentsLog);
//	}
//
//	@Override
//	public void updateUserPaymentsLog(JUserPaymentsLog userPaymentsLog) {
//		userLogRepository.save(userPaymentsLog);
//	}

	@Override
	public List<JUserPaymentsLog> getByUser(JUser user) {
		return userPaymentsLogRepository.getByUser(user);
	}

	@Override
	public void SaveUserPayment(JUser user, JLink link, double amount, String paySystem) {
		if ((user == null)||(link == null)) {
			//throw new exception...
		}
		JUserPaymentsLog userPaymentsLog = new JUserPaymentsLog(user, link, new Date(), amount, paySystem);
		userPaymentsLogRepository.save(userPaymentsLog);
	}
}