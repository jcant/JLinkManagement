package com.gmail.gm.jcant.JLinkManagement.JPA.Link;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.gmail.gm.jcant.JLinkManagement.JPA.User.JUser;

@Entity
public class JLink {
	@Id
	@GeneratedValue
	private long id;
	
	@Column(nullable = false, unique = true)
	private String url;
	
	private String target;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private JUser user;
	
	@Column(nullable = false)
	private Date startDate;
	
	@Column(nullable = false)
	private Date endDate;

	public JLink() {
	}

	public JLink(String url, String target, JUser user, Date startDate, Date endDate) {
		this.url = url;
		this.target = target;
		this.user = user;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public JUser getUser() {
		return user;
	}

	public void setUser(JUser user) {
		this.user = user;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("JLink [id=");
		builder.append(id);
		builder.append(", url=");
		builder.append(url);
		builder.append(", target=");
		builder.append(target);
		builder.append(", user=");
		builder.append(user);
		builder.append(", startDate=");
		builder.append(startDate);
		builder.append(", endDate=");
		builder.append(endDate);
		builder.append("]");
		return builder.toString();
	}	
	
	
	
}



