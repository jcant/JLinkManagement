package com.gmail.gm.jcant.JLinkManagement.JPA.RootLink;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class JRootLinkServiceImpl implements JRootLinkService {

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

	@Override
	@Transactional
	public List<JRootLink> getAllRootLinks() {
		return rlinkRepository.findAll();
	}

	@Override
	@Transactional
	public List<JRootLink> getEnabledRootLinks() {
		return rlinkRepository.getByEnabled(true);
	}

	@Override
	public JRootLink getById(long id) throws JRootLinkException {
		if (!rlinkRepository.existsById(id)) {
			throw new JRootLinkException("JRootLink with id=" + id + " is not found!");
		}

		return rlinkRepository.getById(id);
	}

	@Override
	public void save(JRootLink rootLink) {
		rlinkRepository.save(rootLink);
	}

	@Override
	public void deleteById(long id) throws JRootLinkException {
		if (!rlinkRepository.existsById(id)) {
			throw new JRootLinkException("Can't delete: NO RootLink with such id=" + id);
		}
		rlinkRepository.deleteById(id);
	}
}