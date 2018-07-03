package com.gmail.gm.jcant.JLinkManagement.JPA.Advertising;

public class JAdvertisingException extends Exception {

    private static final long serialVersionUID = 1L;

    private String message;

    public JAdvertisingException(){
        super();
    }

    public JAdvertisingException(String message){
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return "JArticleException: " + message;
    }
}