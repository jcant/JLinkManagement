package com.gmail.gm.jcant.JLinkManagement.JPA.UserLog;

public class JUserLogException extends Exception {

	private static final long serialVersionUID = 1L;

	private String message;

	public JUserLogException() {
		super();
	}

	public JUserLogException(String message) {
		super(message);
		this.message = message;
	}

	@Override
	public String getMessage() {
		return "JArticleException: " + message;
	}
}