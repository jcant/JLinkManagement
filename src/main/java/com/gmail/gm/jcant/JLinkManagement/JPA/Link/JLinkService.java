package com.gmail.gm.jcant.JLinkManagement.JPA.Link;

import java.util.List;
import com.gmail.gm.jcant.JLinkManagement.JPA.User.JUser;

public interface JLinkService {
	JLink findById(long id) throws JLinkException;
	JLink findByUrl(String url);
	
	List<JLink> findByUserAll(JUser user);
	List<JLink> findByUserFree(JUser user);
	List<JLink> findByUserPaid(JUser user);
	
    boolean existsByUrl(String url);
    
    void addLink(JLink link);
    void updateLink(JLink link);
    
}
