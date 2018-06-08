package com.gmail.gm.jcant.JLinkManagement.JPA.RootLink;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class JRootLinkServiceImpl implements JRootLinkService{
	
	@Autowired
    private JRootLinkRepository rlinkRepository;

    @Override
    @Transactional(readOnly = true)
    public JRootLink getRootLinkByUrl(String url) {
        return rlinkRepository.findByUrl(url);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByUrl(String url) {
        return rlinkRepository.existsByUrl(url);
    }

    @Override
    @Transactional
    public void addRootLink(JRootLink link) {
        rlinkRepository.save(link);
    }

    @Override
    @Transactional
    public void updateRootLink(JRootLink link) {
        rlinkRepository.save(link);
    }

}