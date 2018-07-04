package com.gmail.gm.jcant.JLinkManagement.JPA.Link;

import java.util.Date;
import java.util.List;
import com.gmail.gm.jcant.JLinkManagement.JPA.User.JUser;

public interface JLinkService {
	JLink findById(long id) throws JLinkException;
	//JLink findByUrl(String url);
	JLink getByUrlActualEnabled(String url, Date date);
	
	List<JLink> findByUserAll(JUser user, Date date);
	List<JLink> findByUserFree(JUser user, Date date);
	List<JLink> findByUserPaid(JUser user, Date date);
	
    boolean existsByUrl(String url);
	//This function find JLinks with considering Date conditions
    boolean isFreeByUrl(String url);
    
    void addLink(JLink link);
    void updateLink(JLink link);
	void deleteById(long id) throws JLinkException;
    
}
