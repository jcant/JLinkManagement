package com.gmail.gm.jcant.JLinkManagement.JPA.User;

public interface JLinkUserService {
    JLinkUser getUserByLogin(String login);
    boolean existsByLogin(String login);
    void addUser(JLinkUser user);
    void updateUser(JLinkUser user);
}
