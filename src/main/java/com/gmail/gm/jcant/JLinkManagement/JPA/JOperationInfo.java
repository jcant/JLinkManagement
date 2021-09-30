package com.gmail.gm.jcant.JLinkManagement.JPA;

public class JOperationInfo<T> {
	private String message;
	private boolean success;
	private T operationObject;

	public JOperationInfo() {
	}

	public JOperationInfo(String message, boolean success) {
		this.message = message;
		this.success = success;
	}

	public JOperationInfo(String message, boolean success, T operationObject) {
		this.message = message;
		this.success = success;
		this.operationObject = operationObject;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public T getOperationObject() {
		return operationObject;
	}

	public void setOperationObject(T operationObject) {
		this.operationObject = operationObject;
	}

	@Override
	public String toString() {
		return "JUserOperationInfo{" + "message='" + message + '\'' + ", success=" + success + ", operationObject="
				+ operationObject + '}';
	}
}
