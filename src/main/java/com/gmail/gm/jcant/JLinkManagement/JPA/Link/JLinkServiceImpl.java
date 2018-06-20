package com.gmail.gm.jcant.JLinkManagement.JPA.Link;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gmail.gm.jcant.JLinkManagement.JPA.User.JUser;

@Service
public class JLinkServiceImpl implements JLinkService{
	
	@Autowired
    private JLinkRepository linkRepository;
	
	@Override
	@Transactional(readOnly = true)
	public JLink findById(long id) throws JLinkException {
		JLink link = linkRepository.getOne(id);
		if (link == null) {
			throw new JLinkException();
		}
		return link;
	}

	@Override
    @Transactional(readOnly = true)
    public JLink findByUrl(String url) {
        return linkRepository.findByUrl(url);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByUrl(String url) {
        return linkRepository.existsByUrl(url);
    }

    @Override
    @Transactional
    public void addLink(JLink link) {
        linkRepository.save(link);
    }

    @Override
    @Transactional
    public void updateLink(JLink link) {
        linkRepository.save(link);
    }
    
    @Override
    @Transactional
    public List<JLink> findByUser(JUser user) {
        return linkRepository.findByUser(user);
    }

}
