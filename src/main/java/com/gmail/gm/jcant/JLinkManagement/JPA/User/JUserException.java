package com.gmail.gm.jcant.JLinkManagement.JPA.User;

public class JUserException extends Exception {

 	private static final long serialVersionUID = 1L;

	public JUserException(){
        super();
    }

    public JUserException(String message){
        super(message);
    }

    @Override
    public String getMessage() {
        return "JUserException";
    }
}
