package com.gmail.gm.jcant.JLinkManagement.JPA.User;

import javax.persistence.*;

import org.hibernate.annotations.Proxy;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Proxy(lazy = false)
public class JUser {
	@Id
	@GeneratedValue
	private long id;

	private String login;
	
	@JsonIgnore
	private String password;

	@Enumerated(EnumType.STRING)
	private JUserRole role;

	private String email;
	private String name;
	
	private boolean blocked;
	private boolean resetPassword;

	public JUser() {
	}

	public JUser(String login, String password, JUserRole role) {
		this.login = login;
		this.password = password;
		this.role = role;
	}

	public JUser(String login, String password, JUserRole role, String email) {
		this.login = login;
		this.password = password;
		this.role = role;
		this.email = email;
		this.blocked = false;
		this.resetPassword = false;
	}

	public JUser(String login, String password, JUserRole role, String email, String name, boolean blocked, boolean resetPassword) {
		this.login = login;
		this.password = password;
		this.role = role;
		this.email = email;
		this.name = name;
		this.blocked = blocked;
		this.resetPassword = resetPassword;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public JUserRole getRole() {
		return role;
	}

	public void setRole(JUserRole role) {
		this.role = role;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isBlocked() {
		return blocked;
	}

	public boolean isResetPassword() {
		return resetPassword;
	}

	public void setBlocked(boolean blocked) {
		this.blocked = blocked;
	}

	public void setResetPassword(boolean resetPassword) {
		this.resetPassword = resetPassword;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("JUser [id=");
		builder.append(id);
		builder.append(", login=");
		builder.append(login);
		builder.append(", role=");
		builder.append(role);
		builder.append(", email=");
		builder.append(email);
		builder.append(", name=");
		builder.append(name);
		builder.append(", blocked=");
		builder.append(blocked);
		builder.append(", resetPassword=");
		builder.append(resetPassword);
		builder.append("]");
		return builder.toString();
	}

}