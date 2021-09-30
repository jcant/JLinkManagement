package com.gmail.gm.jcant.JLinkManagement.JPA.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class JUserServiceImpl implements JUserService {
	@Autowired
	private JUserRepository userRepository;

	@Override
	@Transactional(readOnly = true)
	public List<JUser> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public List<JUser> getUsersByRole(JUserRole role) {
		return userRepository.findByRole(role);
	}

	@Override
	@Transactional(readOnly = true)
	public JUser getUserById(long id) throws JUserException {
		JUser user = null;
		try {
			user = userRepository.getById(id);
		} catch (HttpMessageNotWritableException e) {
			throw new JUserException("NO USER FOUND FOR ID = " + id);
		}
		if (user == null) {
			throw new JUserException();
		}
		return user;
	}

	@Override
	@Transactional(readOnly = true)
	public JUser getUserByLogin(String login) throws JUserException {
		JUser user = userRepository.findByLogin(login);
		if (user == null) {
			throw new JUserException("user with login=" + login + " is not found!");
		}
		return user;
	}

	@Override
	@Transactional(readOnly = true)
	public boolean existsByLogin(String login) {
		return userRepository.existsByLogin(login);
	}

	@Override
	@Transactional(readOnly = true)
	public boolean existsById(long id) {
		return userRepository.existsById(id);
	}

	@Override
	@Transactional
	public void addUser(JUser user) {
		userRepository.save(user);
	}

	@Override
	@Transactional
	public void updateUser(JUser user) {
		userRepository.save(user);
	}

	@Override
	@Transactional
	public void deleteUser(long id) {
		userRepository.deleteById(id);
	}

}