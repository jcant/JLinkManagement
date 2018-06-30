package com.gmail.gm.jcant.JLinkManagement.JPA.UserLog;

import java.util.Date;
import java.util.List;

import com.gmail.gm.jcant.JLinkManagement.JPA.LinkClick.JLinkClick;
import org.springframework.beans.factory.annotation.Autowired;

import com.gmail.gm.jcant.JLinkManagement.JPA.User.JUser;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class JUserLogServiceImpl implements JUserLogService{

	@Autowired
    private JUserLogRepository userLogRepository;
	
//	@Override
//	public List<JLinkClick> getByUrl(String url) {
//		return linkClickRepository.getByUrl(url);
//	}

//	@Override
//	public void addUserLog(JUserLog userLog) {
//		userLogRepository.save(userLog);
//	}
//
//	@Override
//	public void updateUserLog(JUserLog userLog) {
//		userLogRepository.save(userLog);
//
//	}

	@Override
	public List<JUserLog> getByUser(JUser user) {
		return userLogRepository.getByUser(user);
	}

	@Override
	public void SaveUserComeIn(JUser user, HttpServletRequest request) {
		if ((user == null)||(request == null)) {
			//throw new exception...
		}
		JUserLog userLog = new JUserLog(user, new Date(), request.getRemoteAddr());
		userLogRepository.save(userLog);
	}
}