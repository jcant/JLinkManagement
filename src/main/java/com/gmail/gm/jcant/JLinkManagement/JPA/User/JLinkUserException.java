package com.gmail.gm.jcant.JLinkManagement.JPA.User;

public class JLinkUserException extends Exception {

    public JLinkUserException(){
        super();
    }

    public JLinkUserException(String message){
        super(message);
    }

    @Override
    public String getMessage() {
        return "JLinkUserException";
    }
}
