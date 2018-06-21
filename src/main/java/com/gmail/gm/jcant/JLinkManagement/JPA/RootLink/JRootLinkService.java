package com.gmail.gm.jcant.JLinkManagement.JPA.RootLink;

import java.util.List;

public interface JRootLinkService {
	JRootLink getRootLinkByUrl(String url);
    boolean existsByUrl(String url);
    void addRootLink(JRootLink link);
    void updateRootLink(JRootLink link);
    List<JRootLink> getAllRootLinks();
}