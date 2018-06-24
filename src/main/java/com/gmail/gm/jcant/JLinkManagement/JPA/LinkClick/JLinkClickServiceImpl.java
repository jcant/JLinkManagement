package com.gmail.gm.jcant.JLinkManagement.JPA.LinkClick;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gmail.gm.jcant.JLinkManagement.JPA.User.JUser;

@Service
public class JLinkClickServiceImpl implements JLinkClickService{

	@Autowired
    private JLinkClickRepository linkClickRepository;
	
	@Override
	public List<JLinkClick> getByUrl(String url) {
		return linkClickRepository.getByUrl(url);
	}

	@Override
	public void addLinkClick(JLinkClick linkClick) {
		linkClickRepository.save(linkClick);
	}

	@Override
	public void updateLinkClick(JLinkClick linkClick) {
		linkClickRepository.save(linkClick);
		
	}

	@Override
	public List<JLinkClick> getByUser(JUser user) {
		//System.out.println("in JLinkClickServiceImpl");
//		List<JLinkClick> result = linkClickRepository.getByUser(user);

		//for (JLinkClick jLinkClick : result) {
		//	System.out.println("*** - "+jLinkClick);
		//}

//		return result;

		return linkClickRepository.getByUser(user);
	}
	
}
