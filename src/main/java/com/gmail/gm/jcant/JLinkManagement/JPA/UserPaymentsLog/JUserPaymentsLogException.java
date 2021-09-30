package com.gmail.gm.jcant.JLinkManagement.JPA.UserPaymentsLog;

public class JUserPaymentsLogException extends Exception {

	private static final long serialVersionUID = 1L;

	private String message;

	public JUserPaymentsLogException() {
		super();
	}

	public JUserPaymentsLogException(String message) {
		super(message);
		this.message = message;
	}

	@Override
	public String getMessage() {
		return "JArticleException: " + message;
	}
}