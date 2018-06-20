package com.gmail.gm.jcant.JLinkManagement.JPA.Link;

public class JLinkException extends Exception {

 	private static final long serialVersionUID = 1L;
 	
 	private String message;

	public JLinkException(){
        super();
    }

    public JLinkException(String message){
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return "JLinkException: " + message;
    }
}
