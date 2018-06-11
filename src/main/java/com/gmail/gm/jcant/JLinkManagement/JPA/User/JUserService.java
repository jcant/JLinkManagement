package com.gmail.gm.jcant.JLinkManagement.JPA.User;

import java.util.List;

public interface JUserService {
	
	List<JUser> getAllUsers();
    JUser getUserById (long id) throws JUserException;
	JUser getUserByLogin(String login);
    
	boolean existsByLogin(String login);
    boolean existsById(long id);
    
    void addUser(JUser user);
    
    void updateUser(JUser user);

    void deleteUser(long id);
}
