package com.gmail.gm.jcant.JLinkManagement.JPA.RootLink;

public interface JRootLinkService {
	JRootLink getRootLinkByUrl(String url);
    boolean existsByUrl(String url);
    void addRootLink(JRootLink link);
    void updateRootLink(JRootLink link);
    
}