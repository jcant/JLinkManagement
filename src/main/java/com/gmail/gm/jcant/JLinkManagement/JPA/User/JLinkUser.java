package com.gmail.gm.jcant.JLinkManagement.JPA.User;

import javax.persistence.*;

@Entity
public class JLinkUser {
    @Id
    @GeneratedValue
    private long id;

    private String login;
    private String password;

    @Enumerated(EnumType.STRING)
    private JlinkUserRole role;

    private String email;
    private String name;

    public JLinkUser() {
    }

    public JLinkUser(String login, String password, JlinkUserRole role) {
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public JLinkUser(String login, String password, JlinkUserRole role, String email) {
        this.login = login;
        this.password = password;
        this.role = role;
        this.email = email;
    }

    public JLinkUser(String login, String password, JlinkUserRole role, String email, String name) {
        this.login = login;
        this.password = password;
        this.role = role;
        this.email = email;
        this.name = name;
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

    public JlinkUserRole getRole() {
        return role;
    }

    public void setRole(JlinkUserRole role) {
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("JLinkUser [id=");
		builder.append(id);
		builder.append(", login=");
		builder.append(login);
		builder.append(", role=");
		builder.append(role);
		builder.append(", email=");
		builder.append(email);
		builder.append(", name=");
		builder.append(name);
		builder.append("]");
		return builder.toString();
	}
    
    
}