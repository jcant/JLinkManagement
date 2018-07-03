package com.gmail.gm.jcant.JLinkManagement.JPA.Article;

public class JArticleException extends Exception {

private static final long serialVersionUID = 1L;
 	
 	private String message;

	public JArticleException(){
        super();
    }

    public JArticleException(String message){
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return "JArticleException: " + message;
    }
}
