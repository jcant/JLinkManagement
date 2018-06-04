package com.gmail.gm.jcant.JLinkManagement.JPA.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class JLinkUserServiceImpl implements JLinkUserService{
    @Autowired
    private JLinkUserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public JLinkUser getUserByLogin(String login) {
        return userRepository.findByLogin(login);
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
    public void addUser(JLinkUser user) {
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void updateUser(JLinkUser user) {
        userRepository.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public List<JLinkUser> getAllUsers(){
        return userRepository.findAll();
    }
    @Override
    @Transactional(readOnly = true)
    public JLinkUser getUserById(long id){
        return userRepository.getOne(id);
    }

    @Override
    @Transactional
    public void deleteUser(long id){
        userRepository.deleteById(id);
    }

}