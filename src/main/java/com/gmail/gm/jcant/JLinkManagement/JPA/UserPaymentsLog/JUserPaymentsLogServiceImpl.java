package com.gmail.gm.jcant.JLinkManagement.JPA.UserPaymentsLog;

import java.util.Date;
import java.util.List;

import com.gmail.gm.jcant.JLinkManagement.JPA.Link.JLink;
import org.springframework.beans.factory.annotation.Autowired;

import com.gmail.gm.jcant.JLinkManagement.JPA.User.JUser;

import org.springframework.stereotype.Service;

@Service
public class JUserPaymentsLogServiceImpl implements JUserPaymentsLogService {

	@Autowired
	private JUserPaymentsLogRepository userPaymentsLogRepository;

	@Override
	public List<JUserPaymentsLog> getByUser(JUser user) {
		return userPaymentsLogRepository.getByUser(user);
	}

	@Override
	public void SaveUserPayment(JUser user, JLink link, double amount, String paySystem)
			throws JUserPaymentsLogException {
		if ((user == null) || (link == null)) {
			throw new JUserPaymentsLogException("SaveUserPayment(): user or link is null!");
		}
		JUserPaymentsLog userPaymentsLog = new JUserPaymentsLog(user, link, new Date(), amount, paySystem);
		userPaymentsLogRepository.save(userPaymentsLog);
	}
}