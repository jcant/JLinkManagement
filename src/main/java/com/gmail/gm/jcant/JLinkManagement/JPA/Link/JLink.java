package com.gmail.gm.jcant.JLinkManagement.JPA.Link;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Proxy;

import com.gmail.gm.jcant.JLinkManagement.JPA.User.JUser;

@Entity
@Proxy(lazy = false)
public class JLink {
	@Id
	@GeneratedValue
	private long id;
	
	@Column(nullable = false)
	private String url;
	
	private String target;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private JUser user;
	
	@Column(nullable = false)
	private Date startDate;
	
	@Column(nullable = false)
	private Date finishDate;
	
	@Column(nullable = false)
	private boolean enabled;
	
	@Column(nullable = false)
	private boolean free;

	public JLink() {
	}

	public JLink(String url, String target, JUser user, Date startDate, Date finishDate) {
		this.url = url;
		this.target = target;
		this.user = user;
		this.startDate = startDate;
		this.finishDate = finishDate;
		this.enabled = true;
		this.free = true;
	}
	
	public JLink(String url, String target, JUser user, Date startDate, Date finishDate, boolean enabled, boolean free) {
		this.url = url;
		this.target = target;
		this.user = user;
		this.startDate = startDate;
		this.finishDate = finishDate;
		this.enabled = enabled;
		this.free = free;
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

	public Date getFinishDate() {
		return finishDate;
	}

	public void setFinishDate(Date finishDate) {
		this.finishDate = finishDate;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public boolean isFree() {
		return free;
	}

	public void setFree(boolean free) {
		this.free = free;
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
		//builder.append(", user=");
		//builder.append(user);
		builder.append(", startDate=");
		builder.append(startDate);
		builder.append(", finishDate=");
		builder.append(finishDate);
		builder.append("]");
		builder.append(", enabled=");
		builder.append(enabled);
		builder.append(", free=");
		builder.append(free);
		return builder.toString();
	}	
	
	
	
}



