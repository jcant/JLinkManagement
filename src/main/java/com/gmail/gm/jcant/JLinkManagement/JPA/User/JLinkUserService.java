package com.gmail.gm.jcant.JLinkManagement.JPA.User;

import java.util.List;

public interface JLinkUserService {
	
	List<JLinkUser> getAllUsers();
    JLinkUser getUserById (long id) throws JLinkUserException;
	JLinkUser getUserByLogin(String login);
    
	boolean existsByLogin(String login);
    boolean existsById(long id);
    
    void addUser(JLinkUser user);
    
    void updateUser(JLinkUser user);

    void deleteUser(long id);
}
