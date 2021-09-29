package com.gmail.gm.jcant.JLinkManagement.JPA.Link;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gmail.gm.jcant.JLinkManagement.JPA.LinkClick.JLinkClickRepository;
import com.gmail.gm.jcant.JLinkManagement.JPA.User.JUser;

@Service
public class JLinkServiceImpl implements JLinkService{
	
	@Autowired
    private JLinkRepository linkRepository;
	@Autowired
    private JLinkClickRepository linkClickRepository;
	
	@Override
	@Transactional(readOnly = true)
	public JLink findById(long id) throws JLinkException {
		JLink link = linkRepository.getById(id);
		if (link == null) {
			throw new JLinkException();
		}
		return link;
	}

    @Override
    @Transactional(readOnly = true)
    public JLink getByUrlActualEnabled(String url, Date date) {
        return linkRepository.getByUrlActualEnabled(url, date);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByUrl(String url) {
        return linkRepository.existsByUrl(url);
    }

    @Override
    @Transactional(readOnly = true)
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
    @Transactional(readOnly = true)
    public List<JLink> findByUserAll(JUser user, Date date) {
        return linkRepository.findByUserAll(user, date);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<JLink> findByUserFree(JUser user, Date date) {
        if (date != null) {
        	return linkRepository.findByUserFreeDate(user, date);
        } else {
        	return linkRepository.findByUserFree(user);
        }
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<JLink> findByUserPaid(JUser user, Date date) {
    	if (date != null) {
        	return linkRepository.findByUserPaidDate(user, date);
        } else {
        	return linkRepository.findByUserPaid(user);
        }
    }
    
    @Override
    @Transactional
    public void deleteById(long id) throws JLinkException {
        //JLink link = linkRepository.getOne(id);
    	if (!linkRepository.existsById(id)){
        //if (link == null) {
            throw new JLinkException("Can't delete: NO Link with such id="+id);
        }
        //linkClickRepository.deleteByLink(link);
    	linkClickRepository.deleteByLinkId(id);
        linkRepository.deleteById(id);
    }

}
