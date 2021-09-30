package com.gmail.gm.jcant.JLinkManagement.JPA.RootLink;

import java.util.List;

public interface JRootLinkService {
	JRootLink getRootLinkByUrl(String url);

	boolean existsByUrl(String url);

	void addRootLink(JRootLink link);

	void updateRootLink(JRootLink link);

	List<JRootLink> getAllRootLinks();

	List<JRootLink> getEnabledRootLinks();

	JRootLink getById(long id) throws JRootLinkException;

	void save(JRootLink rootLink);

	void deleteById(long id) throws JRootLinkException;
}