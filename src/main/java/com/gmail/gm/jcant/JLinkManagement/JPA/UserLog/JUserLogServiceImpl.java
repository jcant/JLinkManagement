package com.gmail.gm.jcant.JLinkManagement.JPA.UserLog;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gmail.gm.jcant.JLinkManagement.JPA.User.JUser;
import org.springframework.stereotype.Service;

@Service
public class JUserLogServiceImpl implements JUserLogService{

	@Autowired
    private JUserLogRepository userLogRepository;
	
//	@Override
//	public List<JLinkClick> getByUrl(String url) {
//		return linkClickRepository.getByUrl(url);
//	}

	@Override
	public void addUserLog(JUserLog userLog) {
		userLogRepository.save(userLog);
	}

	@Override
	public void updateUserLog(JUserLog userLog) {
		userLogRepository.save(userLog);
		
	}

	@Override
	public List<JUserLog> getByUser(JUser user) {
		return userLogRepository.getByUser(user);
	}
	
}