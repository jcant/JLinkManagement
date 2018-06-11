package com.gmail.gm.jcant.JLinkManagement.JPA.LinkClick;

import java.util.List;

import com.gmail.gm.jcant.JLinkManagement.JPA.User.JUser;

public interface JLinkClickService {
	List<JLinkClick> getByUrl(String url);
    //boolean existsByUrl(String url);
    void addLinkClick(JLinkClick linkClick);
    void updateLinkClick(JLinkClick linkClick);
    List<JLinkClick> getByUser(JUser user);
}
