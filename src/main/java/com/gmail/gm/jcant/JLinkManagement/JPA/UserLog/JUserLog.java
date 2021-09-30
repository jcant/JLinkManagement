package com.gmail.gm.jcant.JLinkManagement.JPA.UserLog;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.gmail.gm.jcant.JLinkManagement.JPA.User.JUser;

@Entity
public class JUserLog {
	@Id
	@GeneratedValue
	private long id;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private JUser user;

	private Date date;

	private String ip;

	public JUserLog() {
	}

	public JUserLog(JUser user, Date date, String ip) {
		super();
		this.user = user;
		this.date = date;
		this.ip = ip;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public JUser getUser() {
		return user;
	}

	public void setUser(JUser user) {
		this.user = user;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("JUserLog [id=");
		builder.append(id);
		builder.append(", user=");
		builder.append(user);
		builder.append(", date=");
		builder.append(date);
		builder.append(", ip=");
		builder.append(ip);
		builder.append("]");
		return builder.toString();
	}

}
