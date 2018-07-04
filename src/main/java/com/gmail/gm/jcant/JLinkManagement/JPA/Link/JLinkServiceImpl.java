package com.gmail.gm.jcant.JLinkManagement.JPA.Link;

import java.util.Date;
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
    public JLink getByUrlActualEnabled(String url, Date date) {
        return linkRepository.getByUrlActualEnabled(url, date);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByUrl(String url) {
        return linkRepository.existsByUrl(url);
    }

    @Override
    public boolean isFreeByUrl(String url) {
        return linkRepository.isFreeByUrlOnDate(url, new Date());
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
    public List<JLink> findByUserAll(JUser user, Date date) {
        return linkRepository.findByUserAll(user, date);
    }
    
    @Override
    @Transactional
    public List<JLink> findByUserFree(JUser user, Date date) {
        return linkRepository.findByUserFree(user, date);
    }
    
    @Override
    @Transactional
    public List<JLink> findByUserPaid(JUser user, Date date) {
        return linkRepository.findByUserPaid(user, date);
    }
    
    @Override
    public void deleteById(long id) throws JLinkException {
        if (!linkRepository.existsById(id)){
            throw new JLinkException("Can't delete: NO Link with such id="+id);
        }
        linkRepository.deleteById(id);
    }

}
