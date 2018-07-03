package com.gmail.gm.jcant.JLinkManagement.JPA.RootLink;

public class JRootLinkException extends Exception {

    private static final long serialVersionUID = 1L;

    private String message;

    public JRootLinkException(){
        super();
    }

    public JRootLinkException(String message){
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return "JRootLinkException: " + message;
    }
}