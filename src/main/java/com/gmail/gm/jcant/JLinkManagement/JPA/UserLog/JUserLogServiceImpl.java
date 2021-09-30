package com.gmail.gm.jcant.JLinkManagement.JPA.UserLog;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gmail.gm.jcant.JLinkManagement.JPA.User.JUser;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class JUserLogServiceImpl implements JUserLogService {

	@Autowired
	private JUserLogRepository userLogRepository;

	@Override
	public List<JUserLog> getByUser(JUser user) {
		return userLogRepository.getByUser(user);
	}

	@Override
	public void SaveUserComeIn(JUser user, HttpServletRequest request) throws JUserLogException {
		if ((user == null) || (request == null)) {
			throw new JUserLogException("SaveUserComeIn(): user or request is null!");
		}
		JUserLog userLog = new JUserLog(user, new Date(), request.getRemoteAddr());
		userLogRepository.save(userLog);
	}
}