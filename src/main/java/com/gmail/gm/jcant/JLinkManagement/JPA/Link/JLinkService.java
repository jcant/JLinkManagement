package com.gmail.gm.jcant.JLinkManagement.JPA.Link;

import java.util.List;
import com.gmail.gm.jcant.JLinkManagement.JPA.User.JUser;

public interface JLinkService {
	JLink getLinkByUrl(String url);
    boolean existsByUrl(String url);
    void addLink(JLink link);
    void updateLink(JLink link);
    List<JLink> getLinksByUser(JUser user);
}
