package com.gmail.gm.jcant.JLinkManagement.JPA.UserPaymentsLog;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gmail.gm.jcant.JLinkManagement.JPA.User.JUser;

public class JUserPaymentsLogServiceImpl implements JUserPaymentsLogService{

	@Autowired
    private JUserPaymentsLogRepository userLogRepository;
	
//	@Override
//	public List<JLinkClick> getByUrl(String url) {
//		return linkClickRepository.getByUrl(url);
//	}

	@Override
	public void addUserPaymentsLog(JUserPaymentsLog userPaymentsLog) {
		userLogRepository.save(userPaymentsLog);
	}

	@Override
	public void updateUserPaymentsLog(JUserPaymentsLog userPaymentsLog) {
		userLogRepository.save(userPaymentsLog);
	}

	@Override
	public List<JUserPaymentsLog> getByUser(JUser user) {
		return userLogRepository.getByUser(user);
	}
	
}