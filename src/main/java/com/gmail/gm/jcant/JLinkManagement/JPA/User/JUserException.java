package com.gmail.gm.jcant.JLinkManagement.JPA.User;

public class JUserException extends Exception {

 	private static final long serialVersionUID = 1L;
 	
 	private String message;

	public JUserException(){
        super();
    }

    public JUserException(String message){
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return "JUserException: " + message;
    }
}
